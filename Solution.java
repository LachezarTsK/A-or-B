import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
  private static int max_bits_toChange;
  private static StringBuilder[] a_binary;
  private static StringBuilder[] b_binary;
  private static StringBuilder[] c_binary;
  private static Map<Character, String> hexadecimalToBinary;
  private static Map<String, Character> binaryToHexadecimal;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numberOfQueries = scanner.nextInt();
    initilialize_hexadecimalToBinary();
    initilialize_binaryToHexadecimal();

    while (numberOfQueries-- > 0) {
      max_bits_toChange = scanner.nextInt();
      String a_hexadecimal = scanner.next();
      String b_hexadecimal = scanner.next();
      String c_hexadecimal = scanner.next();

      a_binary = createArray_hexadecimalToBinary(a_hexadecimal);
      b_binary = createArray_hexadecimalToBinary(b_hexadecimal);
      c_binary = createArray_hexadecimalToBinary(c_hexadecimal);

      calculateResults();
      printResults();
    }
    scanner.close();
  }

  private static void printResults() {

    if (max_bits_toChange < 0) {
      System.out.println(-1);
      return;
    }

    String a_resultsHexadecimal = createString_binaryToHexadecimal(a_binary);
    String b_resultsHexadecimal = createString_binaryToHexadecimal(b_binary);

    System.out.println(a_resultsHexadecimal);
    System.out.println(b_resultsHexadecimal);
  }

  private static void calculateResults() {

    for (int i = 0; i < c_binary.length && max_bits_toChange >= 0; i++) {
      makeMandatoryChanges_a_bitwiseOR_b_equals_c(i);
    }

    for (int i = 0; i < c_binary.length && max_bits_toChange > 0; i++) {
      reduceValueOf_a_when_a_bitwiseOR_b_equals_c(i);
    }
  }

  private static void makeMandatoryChanges_a_bitwiseOR_b_equals_c(int index) {

    for (int i = 0; i < 4; i++) {
      if (c_binary[index].charAt(i) == '1') {
        if (a_binary[index].charAt(i) == '0' && b_binary[index].charAt(i) == '0') {
          b_binary[index].setCharAt(i, '1');
          max_bits_toChange--;
        }
      } else if (c_binary[index].charAt(i) == '0') {
        if (a_binary[index].charAt(i) == '1') {
          a_binary[index].setCharAt(i, '0');
          max_bits_toChange--;
        }
        if (b_binary[index].charAt(i) == '1') {
          b_binary[index].setCharAt(i, '0');
          max_bits_toChange--;
        }
      }
    }
  }

  private static void reduceValueOf_a_when_a_bitwiseOR_b_equals_c(int index) {

    for (int i = 0; i < 4; i++) {
      if (c_binary[index].charAt(i) == '1' && max_bits_toChange >= 1) {
        if (a_binary[index].charAt(i) == '1' && b_binary[index].charAt(i) == '1') {
          a_binary[index].setCharAt(i, '0');
          max_bits_toChange--;
        }

        if (a_binary[index].charAt(i) == '1' && max_bits_toChange >= 2) {
          a_binary[index].setCharAt(i, '0');
          b_binary[index].setCharAt(i, '1');
          max_bits_toChange--;
          max_bits_toChange--;
        }
      }
    }
  }

  private static void initilialize_hexadecimalToBinary() {

    hexadecimalToBinary = new HashMap<Character, String>();
    for (int i = 0; i <= 9; i++) {
      char intToChar = (char) ('0' + i);
      String binary = Integer.toBinaryString(i);
      binary = check_add_leadingZeros(binary) + binary;
      hexadecimalToBinary.put(intToChar, binary);
    }

    int decimalValue = 10;
    for (char i = 'A'; i <= 'F'; i++) {
      String binary = Integer.toBinaryString(decimalValue);
      binary = check_add_leadingZeros(binary) + binary;
      hexadecimalToBinary.put(i, binary);
      decimalValue++;
    }
  }

  private static void initilialize_binaryToHexadecimal() {

    binaryToHexadecimal = new HashMap<String, Character>();
    for (Character c : hexadecimalToBinary.keySet()) {
      binaryToHexadecimal.put(hexadecimalToBinary.get(c), c);
    }
    binaryToHexadecimal.put("0000", '0');
  }

  private static String createString_binaryToHexadecimal(StringBuilder[] binary) {

    int index_start = 0;
    while (index_start < binary.length && binary[index_start].toString().equals("0000")) {
      index_start++;
    }

    if (index_start == binary.length) {
      return "0";
    }

    StringBuilder a_resultsHexadecimal = new StringBuilder();
    for (int i = index_start; i < binary.length; i++) {
      a_resultsHexadecimal.append(binaryToHexadecimal.get(binary[i].toString()));
    }

    return a_resultsHexadecimal.toString();
  }

  private static StringBuilder[] createArray_hexadecimalToBinary(String input) {

    StringBuilder[] array = new StringBuilder[input.length()];
    for (int i = 0; i < input.length(); i++) {
      String binary = hexadecimalToBinary.get(input.charAt(i));
      array[i] = new StringBuilder(binary);
    }
    return array;
  }

  private static String check_add_leadingZeros(String binary) {
    if (binary.length() == 4) {
      return "";
    }
    if (binary.length() == 3) {
      return "0";
    }
    if (binary.length() == 2) {
      return "00";
    }
    return "000";
  }
}
