/**
 * String Exercise
Using the String, StringBuilder, and StringBuffer, create an application to present the 
performance difference between the three. How you handle the test and present it should be all
 done within the Java Console, but you are free to test it how you like.
 */

 

 public class Main {
    public static void main(String[] args) {
        if (args.length != 1) System.out.println("Usage: Main filename");

        WordList w = new WordList(args[0]);
        String[] list = w.getList();
        long start; //start time of each procedure below
        long duration;
        
        /** concatenation using String */
        start = System.nanoTime();
        stringConcat(list);
        duration = System.nanoTime() - start;
        System.out.println("String concat took: " + duration + " ms");
        
        /** concatenation using StringBuilder */
        start = System.nanoTime();
        stringBuilderConcat(list);
        duration = System.nanoTime() - start;
        System.out.println("StringBuilder concat took: " + duration + " ms");

        /** concatenation using StringBuffer */
        start = System.nanoTime();
        stringBufferConcat(list);
        duration = System.nanoTime() - start;
        System.out.println("StringBuffer concat took: " + duration + " ms");
    }

    private static String stringConcat(String[] list) {
        String result = "";
        for (int i = 0; i < list.length; i++) {
            result += list[i];
        }
        return result;
    }

    private static String stringBuilderConcat(String[] list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            sb.append(list[i]);
        }
        return sb.toString();
    }

    private static String stringBufferConcat(String[] list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.length; i++) {
            sb.append(list[i]);
        }
        return sb.toString();
    }
 }