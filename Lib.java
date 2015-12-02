public class Lib {
    public static int print0(int i){
        System.out.printf("%d ", i);
        return 0;
    }
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
    public static int[] vec_new(int len){
        return new int[len];
    }
    public static int vec_get(int[] vector, int idx){
        return vector[idx];
    }
    public static int vec_set(int[] vector, int idx, int val){
        vector[idx] = val;
        return 0;
    }
}
