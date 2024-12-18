import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigitalPuzzle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String filename = "";

		while (filename.isEmpty()) {
			System.out.print("Please enter the filename (without extension): ");
			filename = scanner.nextLine().trim();
		}
		String[] numbers = readNumbersFromFile(filename.concat(".txt"));
		if (numbers == null) {
			System.out.println("Error reading numbers from file.");
			return;
		}
		String result = findLongestSequence(numbers);
		System.out.println(result);
	}

	public static String findLongestSequence(String[] numbers) {
		String longestSequence = "";

		for (int i = 0; i < numbers.length; i++) {
			String sequence = buildSequence(numbers, i);
			if (sequence.length() > longestSequence.length()) {
				longestSequence = sequence;
			}
		}

		return longestSequence;
	}

	private static String[] readNumbersFromFile(String filename) {
		List<String> numbersList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				numbersList.add(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return numbersList.toArray(new String[0]);
	}

	private static String buildSequence(String[] numbers, int startIndex) {
		List<String> sequence = new ArrayList<>();
		boolean[] used = new boolean[numbers.length];
		sequence.add(numbers[startIndex]);
		used[startIndex] = true;

		while (sequence.size() < numbers.length) {
			String last = sequence.get(sequence.size() - 1);
			String lastTwoDigits = last.substring(last.length() - 2);
			boolean found = false;

			for (int i = 0; i < numbers.length; i++) {
				if (!used[i]) {
					String currentFirstTwoDigits = numbers[i].substring(0, 2);
					if (lastTwoDigits.equals(currentFirstTwoDigits)) {
						sequence.add(numbers[i]);
						used[i] = true;
						found = true;
						break;
					}
				}
			}

			if (!found) {
				break;
			}
		}

		StringBuilder result = new StringBuilder(sequence.get(0));
		for (int i = 1; i < sequence.size(); i++) {
			result.append(sequence.get(i).substring(2));
		}

		return result.toString();
	}
}