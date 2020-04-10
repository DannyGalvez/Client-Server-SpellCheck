import java.io.*;
import java.util.HashSet;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) {

        SpellCheck spell = new SpellCheck();

        System.out.println(spell.SpellCheck("hello"));

        System.out.println(spell.SpellCheck("noj"));

    }
}
