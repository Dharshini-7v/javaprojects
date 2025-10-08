package secondyear;



import java.util.*;
import java.util.regex.*;

public class keywordhighlighter {
    
    // Method to highlight keywords in text
    public static String highlightKeywords(String text, List<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return text;
        }

        // Escape special regex characters in keywords
        List<String> escapedKeywords = new ArrayList<>();
        for (String keyword : keywords) {
            escapedKeywords.add(Pattern.quote(keyword));
        }

        // Create regex pattern with OR for all keywords
        String patternString = String.join("|", escapedKeywords);
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        // Use StringBuffer for efficient replacements
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "**" + matcher.group() + "**"); // highlight with **
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    public static void main(String[] args) {
        String text = "Java is powerful. Python and JavaScript are also popular. JAVA developers like Java.";
        List<String> searchTerms = Arrays.asList("Java", "Python");

        String highlighted = highlightKeywords(text, searchTerms);
        System.out.println("Original Text:\n" + text);
        System.out.println("\nHighlighted Text:\n" + highlighted);
        System.out.println(" ");
        System.out.println("Name:DHARSHINI V");
        System.out.println("Reg.No:2117240020077");
    }
}


