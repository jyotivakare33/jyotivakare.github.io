public class MissingNumber {
     public static void main(String[] args) {
          int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,13,14,15,16,17,18,19,20};
          int sum = 0;
          for(int index = 0; index < numbers.length; index++) {
                sum = sum + index;
          }
          int result = 0;
          for(int index = 1; index <= 20; index++)  {
                result += index;
          }
          System.out.println("missing number is" + result - sum);
      }
}