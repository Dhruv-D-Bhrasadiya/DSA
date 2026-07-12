import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaFileComplexityAnalyzer {

    private static final Pattern METHOD_PATTERN = Pattern.compile(
            "\\b(?:public|private|protected)?\\s*(?:static\\s+)?(?:final\\s+)?(?:[\\w\\[\\].<>?]+)\\s+(\\w+)\\s*\\([^;{}]*\\)\\s*\\{");
    private static final Pattern LOOP_PATTERN = Pattern.compile("\\b(for|while)\\s*\\(([^)]*)\\)");
    private static final Pattern ARRAY_PATTERN = Pattern.compile("\\bnew\\s+\\w+\\s*\\[\\s*([^\\]\\s]+(?:\\.[\\w]+)?)\\s*\\]");
    private static final Pattern BOUND_PATTERN = Pattern.compile("(<|<=|>|>=)\\s*([A-Za-z_][A-Za-z0-9_\\.]*|\\d+)");

    public static void main(String[] args) {
        String filePathString = args.length > 0
                ? args[0]
                : "C:/Users/ADMIN/Desktop/Work/github/DSA/algorithms/graph/3532_path_existence_queries_in_a_graph_1.java";
        Path path = Paths.get(filePathString);

        if (!Files.exists(path)) {
            System.err.println("Error: The file at " + filePathString + " does not exist.");
            return;
        }

        try {
            String fileContent = Files.readString(path);
            System.out.println("=== Analyzing File: " + path.getFileName() + " ===");
            System.out.println("Time Complexity  : " + analyzeTimeComplexity(fileContent));
            System.out.println("Space Complexity : " + analyzeSpaceComplexity(fileContent));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static String analyzeTimeComplexity(String code) {
        String cleanCode = stripComments(code);
        List<String> terms = new ArrayList<>();

        for (String methodBody : extractMethodBodies(cleanCode)) {
            terms.addAll(analyzeMethodTime(methodBody));
        }

        if (terms.isEmpty()) {
            return "O(1)";
        }

        List<String> uniqueTerms = new ArrayList<>();
        for (String term : terms) {
            if (term != null && !term.isBlank() && !uniqueTerms.contains(term)) {
                uniqueTerms.add(term);
            }
        }

        if (uniqueTerms.stream().anyMatch(term -> term.contains("*") || term.contains("^"))) {
            String dominant = uniqueTerms.stream()
                    .filter(term -> term.contains("*") || term.contains("^"))
                    .max(Comparator.comparingInt(JavaFileComplexityAnalyzer::complexityWeight))
                    .orElse(uniqueTerms.get(0));
            return "O(" + dominant + ")";
        }

        uniqueTerms.sort(Comparator.comparingInt(JavaFileComplexityAnalyzer::termRank)
                .thenComparing(String::compareTo));
        return "O(" + String.join(" + ", uniqueTerms) + ")";
    }

    public static String analyzeSpaceComplexity(String code) {
        String cleanCode = stripComments(code);
        List<String> spaceParams = new ArrayList<>();

        Matcher matcher = ARRAY_PATTERN.matcher(cleanCode);
        while (matcher.find()) {
            String normalized = normalizeSizeSymbol(matcher.group(1));
            if (!spaceParams.contains(normalized)) {
                spaceParams.add(normalized);
            }
        }

        if (spaceParams.isEmpty()) {
            return "O(1)";
        }

        spaceParams.sort(Comparator.comparingInt(JavaFileComplexityAnalyzer::termRank)
                .thenComparing(String::compareTo));
        return "O(" + String.join(" + ", spaceParams) + ")";
    }

    private static List<String> analyzeMethodTime(String body) {
        List<LoopInfo> loops = extractLoops(body);
        List<String> terms = new ArrayList<>();

        if (loops.isEmpty()) {
            return terms;
        }

        loops.sort(Comparator.comparingInt(loop -> loop.startIndex));
        List<LoopInfo> activeLoops = new ArrayList<>();

        for (LoopInfo loop : loops) {
            while (!activeLoops.isEmpty() && activeLoops.get(activeLoops.size() - 1).endIndex <= loop.startIndex) {
                activeLoops.remove(activeLoops.size() - 1);
            }

            List<String> chain = new ArrayList<>();
            for (LoopInfo parent : activeLoops) {
                chain.add(parent.variable);
            }
            chain.add(loop.variable);
            terms.add(buildLoopTerm(chain));
            activeLoops.add(loop);
        }

        return terms;
    }

    private static List<String> extractMethodBodies(String code) {
        List<String> bodies = new ArrayList<>();
        Matcher matcher = METHOD_PATTERN.matcher(code);

        while (matcher.find()) {
            String methodName = matcher.group(1);
            if ("main".equals(methodName)) {
                continue;
            }

            int openBrace = code.indexOf('{', matcher.end() - 1);
            int closeBrace = findMatchingBrace(code, openBrace);
            if (openBrace >= 0 && closeBrace > openBrace) {
                bodies.add(code.substring(openBrace + 1, closeBrace));
            }
        }

        return bodies;
    }

    private static List<LoopInfo> extractLoops(String body) {
        List<LoopInfo> loops = new ArrayList<>();
        Matcher matcher = LOOP_PATTERN.matcher(body);

        while (matcher.find()) {
            String loopHeader = matcher.group(0);
            String loopVar = extractLoopVariable(loopHeader);
            if (loopVar == null || loopVar.isBlank()) {
                continue;
            }

            int startIndex = matcher.start();
            int endIndex = findLoopBodyEnd(body, matcher.end());
            loops.add(new LoopInfo(loopVar, startIndex, endIndex));
        }

        return loops;
    }

    private static int findLoopBodyEnd(String body, int headerEnd) {
        int index = headerEnd;
        while (index < body.length() && Character.isWhitespace(body.charAt(index))) {
            index++;
        }

        if (index < body.length() && body.charAt(index) == '{') {
            return findMatchingBrace(body, index);
        }

        int parenDepth = 0;
        int braceDepth = 0;
        int bracketDepth = 0;

        for (int i = index; i < body.length(); i++) {
            char c = body.charAt(i);
            if (c == '(') {
                parenDepth++;
            } else if (c == ')') {
                parenDepth = Math.max(0, parenDepth - 1);
            } else if (c == '{') {
                braceDepth++;
            } else if (c == '}') {
                braceDepth = Math.max(0, braceDepth - 1);
            } else if (c == '[') {
                bracketDepth++;
            } else if (c == ']') {
                bracketDepth = Math.max(0, bracketDepth - 1);
            } else if (c == ';' && parenDepth == 0 && braceDepth == 0 && bracketDepth == 0) {
                return i;
            }
        }

        return body.length();
    }

    private static String buildLoopTerm(List<String> chain) {
        Map<String, Integer> counts = new HashMap<>();
        for (String variable : chain) {
            counts.put(variable, counts.getOrDefault(variable, 0) + 1);
        }

        List<String> factors = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String factor = entry.getKey();
            if (entry.getValue() > 1) {
                factor += "^" + entry.getValue();
            }
            factors.add(factor);
        }

        factors.sort(String::compareTo);
        if (factors.isEmpty()) {
            return "1";
        }
        return String.join(" * ", factors);
    }

    private static int findMatchingBrace(String code, int openBrace) {
        if (openBrace < 0) {
            return -1;
        }

        int depth = 0;
        for (int i = openBrace; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '{') {
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static String extractLoopVariable(String loopHeader) {
        String header = loopHeader.toLowerCase();

        if (header.contains("queries.length") || header.contains("queries")) {
            return "q";
        }
        if (header.contains("nums.length") || header.contains("nums")) {
            return "n";
        }
        if (header.contains("left") || header.contains("right") || header.contains("start") || header.contains("end")) {
            return "n";
        }

        Matcher matcher = BOUND_PATTERN.matcher(loopHeader);
        while (matcher.find()) {
            String bound = matcher.group(2);
            if (bound.equals("n") || bound.equals("nums")) {
                return "n";
            }
            if (bound.equals("q") || bound.equals("queries")) {
                return "q";
            }
            if (bound.matches("\\d+")) {
                continue;
            }
            return normalizeSizeSymbol(bound);
        }

        return "n";
    }

    private static String normalizeSizeSymbol(String expression) {
        String value = expression.trim();
        if (value.isEmpty()) {
            return "n";
        }

        int dotIndex = value.indexOf('.');
        if (dotIndex >= 0) {
            value = value.substring(0, dotIndex).trim();
        }

        if (value.equals("queries") || value.equals("q")) {
            return "q";
        }
        if (value.equals("nums") || value.equals("n")) {
            return "n";
        }
        if (value.equals("left") || value.equals("right") || value.equals("start") || value.equals("end")) {
            return "n";
        }
        if (value.contains("length")) {
            String base = value.substring(0, value.indexOf("length")).trim();
            return normalizeSizeSymbol(base);
        }
        return value;
    }

    private static String stripComments(String code) {
        StringBuilder result = new StringBuilder(code.length());
        boolean inLineComment = false;
        boolean inBlockComment = false;
        boolean inString = false;
        boolean inChar = false;

        for (int i = 0; i < code.length(); i++) {
            char current = code.charAt(i);
            char next = i + 1 < code.length() ? code.charAt(i + 1) : '\0';

            if (inLineComment) {
                if (current == '\n') {
                    inLineComment = false;
                    result.append(current);
                }
                continue;
            }

            if (inBlockComment) {
                if (current == '*' && next == '/') {
                    inBlockComment = false;
                    i++;
                } else if (current == '\n') {
                    result.append(current);
                }
                continue;
            }

            if (inString) {
                result.append(current);
                if (current == '\\' && i + 1 < code.length()) {
                    result.append(code.charAt(++i));
                    continue;
                }
                if (current == '"') {
                    inString = false;
                }
                continue;
            }

            if (inChar) {
                result.append(current);
                if (current == '\\' && i + 1 < code.length()) {
                    result.append(code.charAt(++i));
                    continue;
                }
                if (current == '\'') {
                    inChar = false;
                }
                continue;
            }

            if (current == '/' && next == '/') {
                inLineComment = true;
                i++;
            } else if (current == '/' && next == '*') {
                inBlockComment = true;
                i++;
            } else {
                if (current == '"') {
                    inString = true;
                } else if (current == '\'') {
                    inChar = true;
                }
                result.append(current);
            }
        }

        return result.toString();
    }

    private static int termRank(String term) {
        if (term == null || term.isBlank()) {
            return 99;
        }
        if ("n".equals(term)) {
            return 0;
        }
        if ("q".equals(term)) {
            return 1;
        }
        if ("1".equals(term)) {
            return 2;
        }
        return 3;
    }

    private static int complexityWeight(String term) {
        if (term == null || term.isBlank()) {
            return 0;
        }
        if (term.contains("^")) {
            try {
                return Integer.parseInt(term.substring(term.lastIndexOf('^') + 1));
            } catch (NumberFormatException e) {
                return 2;
            }
        }
        if (term.contains("*")) {
            return 2;
        }
        return 1;
    }

    private static class LoopInfo {
        private final String variable;
        private final int startIndex;
        private final int endIndex;

        private LoopInfo(String variable, int startIndex, int endIndex) {
            this.variable = variable;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }
}