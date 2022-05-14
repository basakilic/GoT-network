package gotproject;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {
    
    HashGraph hashGraph;
    public Menu() {
        this.hashGraph = new HashGraph("GoT.txt");
    }
    
    public void intro() throws FileNotFoundException{
                System.out.print("-------------------------------------\n"
                        + "   Welcome, there are some options for graph here.\n");
                menu();
        }
    
        public void menu() throws FileNotFoundException{
            Scanner input = new Scanner(System.in);
            System.out.print("-------------------------------------\n"
                + "1. Print closest character \n"
                + "2. Print farthest character \n"
                + "3. Is connected? \n"
                + "4. Shortest path  \n"
                + "5. Delete \n"
                + "6. Change connections \n"
                + "7. Number of character group \n"
                + "8. How many neighbors \n"
                + "9. Show all neighbors \n"
                + "10. Quit \n"
                + "Choose the option that you want:");
        
        int option = input.nextInt();
        while (true) {
            if (option == 10) {
                System.out.println("You choose to quit, GOODBYE.");
                break;
            }
                switch (option) {
                    case 1:
                        System.out.println("You choose the option 'Print closes character' ");
                        System.out.println("-------------------------------------");
                        System.out.print("Enter character name: ");
                        String characterName = input.next();
                        System.out.print("Enter threshold value: ");
                        int threshold = input.nextInt();
                        hashGraph.printClosestCharacters(new SeriesCharacter(characterName),threshold);
                        break;
                            
                    case 2:
                        System.out.println("You choose the option 'Print farther character' ");
                        System.out.println("-------------------------------------");
                        System.out.print("Enter character name: ");
                        characterName = input.next();
                        System.out.print("Enter threshold value: ");
                        threshold = input.nextInt();
                        hashGraph.printFartherCharacters(new SeriesCharacter(characterName),threshold);
                        break;
                            
                    case 3:
                        System.out.println("You choose the option 'Is connected?' ");
                        System.out.println("-------------------------------------");
                        System.out.print("Enter first character's name: ");
                        String character1 = input.next();
                        System.out.print("Enter second character's name: ");
                        String character2 = input.next();
                        boolean result = hashGraph.isConnected(new SeriesCharacter(character1), new SeriesCharacter(character2));
                        if(result){
                            System.out.println(character1 + " and " + character2 +" are connected.");
                        }
                        else
                            System.out.println(character1 + " and " + character2 +" are NOT connected.");
                        break;
                            
                    case 4:
                        System.out.println("You choose the option 'Shortest path' ");
                        System.out.println("-------------------------------------");
                        System.out.print("Enter first character's name: ");
                        character1 = input.next();
                        System.out.print("Enter second character's name: ");
                        character2 = input.next();
                        System.out.println("Character 1: " + character1 + " & "+ "Character 2: " + character2);
                        //shortestPath(hashGraph.shortestPath(new SeriesCharacter(character1),new SeriesCharacter(character2));
                        break;
                        
                    case 5:
                        System.out.println("You choose the option 'Delete' ");
                        System.out.println("-------------------------------------");
                        System.out.print("Enter first character's name: ");
                        character1 = input.next();
                        System.out.print("Enter second character's name: ");
                        character2 = input.next();
                        hashGraph.delete(new SeriesCharacter(character1), new SeriesCharacter(character2));
                        break;
                        
                    case 6:
                        System.out.println("You choose the option 'Change connections' ");
                        System.out.println("-------------------------------------");
                        System.out.print("Enter first character's name: ");
                        character1 = input.next();
                        System.out.print("Enter second character's name: ");
                        character2 = input.next();
                        System.out.print("Enter new weight value: ");
                        int w = input.nextInt();
                        hashGraph.change(new SeriesCharacter(character1), new SeriesCharacter(character2), w);
                        break;
                        
                    case 7:
                        System.out.println("You choose the option 'Number of character group' ");
                        System.out.println("-------------------------------------");
                        // numberOfCharacterGroup();
                        break;
                        
                    case 8:
                        System.out.println("You choose the option 'How many neihbors' ");
                        System.out.println("-------------------------------------");
                        System.out.print("Enter character name: ");
                        character1 = input.next();
                        hashGraph.printHowManyNeighbors(new SeriesCharacter(character1));
                        break;
                            
                    case 9:
                        System.out.println("You choose the option 'All neighbors' ");
                        System.out.println("-------------------------------------");
                        System.out.print("Enter character name: ");
                        character1 = input.next();
                        System.out.println(hashGraph.printAllNeighbors(new SeriesCharacter(character1)));
                        break;
                            
                    default:
                        System.out.println("Invalid option! Please enter a valid option :(");
                        break;
                }
            menu();
            break;
        }
    }        
}
