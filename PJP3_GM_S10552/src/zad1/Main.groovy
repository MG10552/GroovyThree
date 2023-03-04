/**
 *
 *  @author Głodowski Michał S10552
 *
 */

package zad1;

import java.text.SimpleDateFormat

// Different test samples defined as separate text for better readability. 
def header = "Dziennik Kapitański."
def d1 = "dzień 2018-12-02: Nic nadzwyczajnego; " // Correct - some date in current month.
def d2 = "dzień 2014-36-12: Szokująco silny sztorm; " // Incorrect - there are only 12 months in a single year.
def d3 = "dzień 2016-02-31: Zorza polarana od południa; " // Incorrect - February never had 31 days.
def d4 = "dzień 2018-2-16: Nic nadzwyczajnego; " // Incorrect - it's yyyy-MM-dd; missing one digit in month which means it will be filtered out by 'if' statement at the beginning.
def d5 = "dzień 4110-12-20: Ławica robodelfinów; " // Correct. 
def d6 = "dzień 0966-04-14: Chrzest Polski; " // Correct - years before 4-digit ones require zeros ("0") at the beginning to match the schema. 
def d7 = "dzień 1-01-01: pierwszy dzień nowej ery; " // Incorrect - violation of format; missing 3 digits. In this case three zeros at the beginning. 
def d8 = " 121-002-423 - numer telefonu, faxu?; "// Incorrect - 'if' should filter this out at the beginning without any notice.

// Combine all test samples into one text.
def sample = header + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8

if (sample != null && sample =~ /\d{4}-\d{2}-\d{2}/) {
	candidates = sample =~ /\d{4}-\d{2}-\d{2}/  
	//Among candidates find all that correctly fulfil schema.
	def result = candidates.findAll({
		try {
			// Create new format and check if samples match the expected results.
			def gregorianDate = new SimpleDateFormat("yyyy-MM-dd")
			gregorianDate.setLenient(false)
			gregorianDate.parse(it)
		}  catch (Exception e) {
			// Should sample be in correct format, but in any shape or form differ or violate Gregorian Calendar rules it will end up here.
			println "Found invalid date attempt! " + it + " is not a Gregorian Calendar date."
	}	
		})
	// All correct Gregorian Calenadar dates will show up here. 
	println "\n \tManaged to find Gregorian Calendar dates: " + result
} else {
	// If there are no dates or dates in sample text are in different than expected format (for example: 1 May 1222) give information that no dates are found.
	println "\n In given sample of text: " + sample
	println "\n \t There are no dates, or dates are not written in expected format."
}
 