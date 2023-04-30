/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bussprojekt;
import java.util.Scanner;

/**
 *
 * @author alina.aristarhovako
 */
public class BussProjekt {

    /**
     * @param args the command line arguments
     */
    static Scanner scan = new Scanner (System.in);
    static int[] platser = {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1};
    static int[] unga = new int[0];
    static int[] medel = new int[0];
    static int[] gamla = new int[0];
    static double pris = 0;
    static double totPris = 0;
    
    public static void main(String[] args) {
        System.out.println("Välkommen till bussen!\n");
        int menyval = 0;
        int personnummer = 0;
        
        while(true) {
            System.out.println("|1 |2 |  |3 |4 |\n|5 |6 |  |7 |8 |\n|9 |10|  |11|12|\n|13|14|  |15|16|\n|17|18|19|20|21|\n\n");

            while (true) {
                System.out.println("Vill du: \n1. Boka sittplats\n2. Hitta bokad plats\n3. Avboka plats");
                menyval = 0;

                try {
                    menyval = scan.nextInt();
                    if(menyval < 0 && menyval > 4) {
                        System.out.println("<ERROR: Välj ett av menyvalen tack.>");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("<ERROR: Välj ett av menyvalen tack.>");
                    scan.nextLine();
                }
            }
            
            while(true) {
                System.out.print("Skriv in ditt personnummer (yyyymmdd): ");
                
                try {
                    personnummer = scan.nextInt();
                    //char pers = (char)personnummer;
                    String persString = Integer.toString(personnummer);
                    int längd = persString.length();
                    /*if (pers.charAt(0) == '0') {
                        längd += 1;
                    }*/
                    if(längd == 8) {
                        break;
                    } else {
                        System.out.println("<ERROR: Personnummer ej giltigt.>");
                    }

                } catch (Exception e) {
                    System.out.println("<ERROR: Personnummer ej giltigt.>");
                    scan.nextLine();
                }
                
                rabatter(personnummer);
            }
            

            switch (menyval) {
                case 1:
                    int platsval = 0;
                    while(true) {
                        System.out.println("Would you like a window seat or standard set? \n1. Standard \n2. Window");
                        try {
                            platsval = scan.nextInt();
                            if(platsval < 0 && platsval > 4) {
                                System.out.println("<ERROR: Välj ett av menyvalen tack.>");
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("<ERROR: Välj ett av menyvalen tack.>");
                            scan.nextLine();
                        }
                    }
                    
                    rabatter(personnummer);
                    if (platsval == 1) {
                        bokaStandard(personnummer);
                    } else {
                        bokaFönster(personnummer);
                    }
                    break;
                case 2:
                    hitta(personnummer);
                    break;
                case 3:
                    avboka(personnummer);
                    break;
                default:
                    break;
            }
            
            System.out.println("Ditt totala pris ligger nu på "+totPris+" kr.");
            System.out.println("Din bokning har just nu bokningar för "+unga.length+" minderåriga, "+medel.length+" över 18 och "+gamla.length+" pensionärer.");
            
            System.out.println("Vill du fortsätta? (1)ja, (2)nej");
            int fortsätt=0;
            try {
                fortsätt = scan.nextInt();
            } catch (Exception e) {
                System.out.println("<ERROR: Personnummer ej giltigt.>");
                scan.nextLine();
            }
            
            if (fortsätt == 2) {
                break;
            }
        }
    }
    

// lägger till element för nya personnummer i listorna för åldersgrupper
    static int[] addArrayElement(int arr[], int add) {
        int index = arr.length;
        int newArr[] = new int[index + 1];
        for(int i=0; i<index; i++) {
            newArr[i] = arr[i];
        }
        newArr[index] = add;
        return newArr;
    }
    

// funktion för att fram ålder och lägga till rabatt
    static void rabatter(int persnum) {
        //gör om intagna personnumret till en string för att kunna ta fram characters
        String persChar = Integer.toString(persnum);
        int age = 0;
        for(int i=0; i<4; i++) {
            //samlar de fyra första characters av personnumret och gör om till int igen
            int persIndex = Integer.parseInt(String.valueOf(persChar.charAt(i)));
            //lägger in talet i rätt tiondelsposition
            switch (i) {
                case 0 -> persIndex *= 1000;
                case 1 -> persIndex *= 100;
                case 2 -> persIndex *= 10;
                default -> {
                }
            }
            age += persIndex;
        }
        
// bestämmer rabatt och lägger till åldersgrupperna i respektive lista
        if(age > 2005) {
            pris = 149.90;
            unga = addArrayElement(unga, persnum);
        } else if(age <= 2005 && age >= 1954) {
            pris = 299.90;
            medel = addArrayElement(medel, persnum);
        } else if(age < 1954) {
            pris = 200.00;
            gamla = addArrayElement(gamla, persnum);
        }
    }
    
    // hittar i vilken åldergrupp personnumret är
    static void findPersnum(int arr[], int persnum) {     
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == persnum) {
                
            }
        }
    }
    
    static void bokaStandard(int persnum) {
        for (int i=0;i<platser.length;i++) {
            if(platser[i]==0) {
                platser[i] = persnum;
                
                int index = -1;
                for (int j=0;j<platser.length;j++) {
                    if(platser[j]== persnum) {
                        index=j;
                        totPris += pris;
                        System.out.println("Det kostar "+pris+" kr.");
                        System.out.println("Din bokade plats har nummer "+(index+1));
                    }
                }
                System.out.println("Tack för din bokning.\n");
                break;
            }
        }
    }
    
    static void bokaFönster(int persnum) {
        for (int i=0;i<platser.length;i++) {
            if(platser[i]==1) {
                platser[i] = persnum;
                
                int index = -1;
                for (int j=0;j<platser.length;j++) {
                    if(platser[j]== persnum) {
                        index=j;
                        totPris += pris;
                        System.out.println("Det kostar "+pris+" kr.");
                        System.out.println("Din bokade plats har nummer "+(index+1));
                    }
                }
                System.out.println("Tack för din bokning.\n");
                break;
            }
            /*if (i > 21) {
                System.out.println("Det finns tyvärr inga lediga platser.");
                break;
            }*/
        }
    }
    
    static void hitta(int persnum) {
        int index = -1;
        for (int i=0;i<platser.length;i++) {
            if(platser[i]== persnum) {
                index=i;
                System.out.println("Din bokade plats har numret "+(index+1));
                break;
            }
        }
    }
    
    static void avboka(int persnum) {
        for (int i=0;i<platser.length;i++) {
            if(platser[i]== persnum) {
                platser[i] = 0;
                totPris -= pris;
                System.out.println("Din plats har blivit avbokad.");
                System.out.println("Betalningen tillbaka blir "+pris+" kr.");
                break;
            } else {
                System.out.println("Din plats finns inte bokad.");
                break;
            }
        }
    }
    
}
