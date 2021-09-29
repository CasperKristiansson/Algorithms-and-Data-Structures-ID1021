/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-29
 * Code Updated: 2021-09-29
 * Problem: Simple text filter which removes all characters that are no alphabetic characters
 * blank or newline.
 * Sources: None
*/
import java.util.Scanner;

public class L3Uppgift1 {
    public static void main(String[] args) {
        textFilter();
    }

    /**
     * Reads the stdin in a while loop using a scanner with nextLine. If a
     * cntrl Z command is given, the loop will exit. We than filter the text
     * by looking through character by character and if the character is not
     * a alphabetic character, newline or space we replace it with a space.
     */
    public static void textFilter() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Text: ");

        StringBuilder sb = new StringBuilder();

        while (true) {
            try {
                sb.append(input.nextLine());
                sb.append('\n');

            } catch (java.util.NoSuchElementException e) {
                break;
            }
        }
        input.close();
        System.out.println("Unfiltered Text: " + sb.toString());

        for(int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if(!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z') && c != ' ' && c != '\n') {
                sb.setCharAt(i, ' ');
            }
        }
        System.out.println("Filtered Text: " + sb.toString());
    }
}
