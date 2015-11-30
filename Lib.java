public class Lib {
    public static int printd(int i){
        System.out.println(i);
        return 0;
    }
    public static int printf(String i){
        System.out.println(i);
        return 0;
    }
    public static int sleep (int i) throws InterruptedException{
        Thread.sleep(i*1000);
        return 0;
    }
    public static String get_char_at(String s, int i){
        return Character.toString(s.charAt(i));
    }
    public static int strlen(String s){
        return s.length();
    }
    public static String put_char_at(String s, int i, String p){
        return s.substring(0,i) + p + s.substring(i+1);
    }
}
