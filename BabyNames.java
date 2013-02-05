import java.util.*;

// A0072292H
// Chong Yun Long

class BabyNames {

	private AVL Mbabies; // separate trees for male and female
	private AVL Fbabies;

	public BabyNames() {
		
		Mbabies = new AVL();
		Fbabies = new AVL();

	}

	void AddSuggestion(String babyName, int genderSuitability) {

		if (genderSuitability == 1)
			Mbabies.insert(babyName);
		else
			Fbabies.insert(babyName);

	}

	int Query(String START, String END, int genderPreference) {
		
		int count = 0;

		if (genderPreference != 2) { // male or both genders

			count += Mbabies.findCount(START, END);
		}
		if (genderPreference != 1) { // female or both genders

			count += Fbabies.findCount(START, END);
		}

		return count;

	}

	void run() {
		// do not alter this method
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		while (N-- > 0)
			AddSuggestion(sc.next(), sc.nextInt());

		int Q = sc.nextInt();
		while (Q-- > 0)
			System.out.println(Query(sc.next(), // START
					sc.next(), // END
					sc.nextInt())); // GENDER
	}

	public static void main(String[] args) {
		// do not alter this method
		BabyNames ps1 = new BabyNames();
		ps1.run();
	}

}
