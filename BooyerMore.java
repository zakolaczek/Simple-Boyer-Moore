import java.util.Scanner;
public class BooyerMore {
    public static void main(String[] args) {
        Solved booyer = new Solved();
        booyer.mainMenu();
    }
}
class Solved{
    boolean booyer(String sentence, String toFind){

        System.out.println("DEBUG:");
        sentence =  sentence.toLowerCase();
        toFind = toFind.toLowerCase();

        int calc;
        int cycle;
        int toFindLength = toFind.length();
        int currentIndex = toFindLength - 1;

        while(currentIndex < sentence.length()){
            System.out.printf("Current last signs (%c, %c)\n", sentence.charAt(currentIndex), toFind.charAt(toFindLength - 1));;
            calc = toFind.length() - 1;
            cycle = 0;
            if(sentence.charAt(currentIndex) != toFind.charAt(toFindLength - 1)){
                if(toFind.contains(Character.toString(sentence.charAt(currentIndex)))){
                    currentIndex += calculateMove(sentence.charAt(currentIndex), toFind);
                    System.out.println("Moved to match letters!");
                }
                else{
                    if(currentIndex + toFindLength < sentence.length()){
                        currentIndex += toFindLength;
                        System.out.println("Moved by toFind length!");
                    }else{
                        System.out.println("\n\nSCORE:");
                        System.out.println("There is no your key in inputted sentence!");
                        return false;
                    }
                }
            }
            else if(sentence.charAt(currentIndex) == toFind.charAt(toFindLength - 1)){
                System.out.println("Looping time!");
                for(int i = currentIndex; i > currentIndex - toFindLength; i--){
                    cycle ++;
                    if(sentence.charAt(i) != toFind.charAt(calc)){
                        currentIndex += cycle - 1;
                        System.out.println("Loop broke, moved to be fine!");
                        break;
                    }
                    if(sentence.charAt(i) == toFind.charAt(calc) && i == currentIndex - toFindLength + 1){
                        System.out.println("\n\nSCORE:");
                        System.out.printf("Boyer-Moore has found your key (%s)\n", toFind);
                        System.out.printf("There is beetween indexes (%d,%d)\n", currentIndex - toFindLength + 1, currentIndex);
                        showScore(sentence, currentIndex - toFindLength + 1, currentIndex);
                        System.out.println("");
                        return true;
                    }
                    calc --;
                }
            }
        }
        System.out.println("\n\nSCORE:");
        System.out.println("There is no your key in inputted sentence!");
        return false;
    }
    int calculateMove(char letter, String toFind){
        int near = 0;
        for(int j = toFind.length() - 1; j >= 0; j--){
            if(toFind.charAt(j) == letter){
                return near;
            }
            near ++;
        }
        return 0;
    }
    void showScore(String sentence, int beginIndex, int endIndex){
        System.out.println("\t" + sentence);
        System.out.print("\t");
        for(int i = 0; i < sentence.length(); i++){
            if(i == beginIndex || i == endIndex){
                System.out.print("*");
            } else if (i > beginIndex && i < endIndex) {
                System.out.print("^");
            } else{
                System.out.print(" ");
            }
        }
    }
    void mainMenu(){
        Scanner ask = new Scanner(System.in);
        System.out.println("Type a sentence to be checked: ");
        String sentence = ask.nextLine();
        System.out.println("Type your key: ");
        String key = ask.nextLine();
        System.out.println(booyer(sentence, key));
    }
}