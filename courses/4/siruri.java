package main.java.curs4;


/*
  Siruri de caractere
  Siruri -> tablouri cu elem de tip char != ideea de sir de caractere C/C++
         -> clase specifice:
String                             StringBuilder                           StringBuffer
--------                            ------------                            -------------
imutabila                           NU e thread safe   <->  MUTABILE   <->   StringBuilder + thread-safe
thread-safe
String s = test;
String t = new String("test");
String w  = new String ("test");
String v = "test";
v = v + "usor";
if (sir1 == sir 2) // compara ref


String s = "test";
System.out.println(s[0]); NU POT ACCESA UN ELEMENT DINTR-UN STRING PRIN INDEXARE
              // scriem s.charAt(0) - returneaza un char
              // setCharAt() - modifica un char
*/


public class siruri {
    public static void main(String[] args) {
    }

    // metode existente in String
    /*
    lenght() - returneaza lungimea sirului
    extragere subsiruri : Substring()
    comparare : compareTo(), equals(), compareToIgnoreCase()
    cautare : indexOf(), lastIndexOf(), contains()

    metode pentru regex
    => matches(String regex)
    ex: boolean ok = s.matches("[A-Z][a-z]+"); // incepe cu litera mare, urmata de lit mici
        boolean ok = s.matches ("[0-9]+"); // doar cifre
    => replaceAll(String regex, String replacement) & replaceFirst(String regex, String replacement)
    Dupa am vb de automate, gramatici si Chomsky ador ❤️
    => split(String regex) - imparte un sir in subsiruri dupa un regex -> tablou siruri
    */
    String s = "Ana are mere, pere, prune si pisici!";
    // String [] w = s.split(regex: "[,.!? ]+");
}