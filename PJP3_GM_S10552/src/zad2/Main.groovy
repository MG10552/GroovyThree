/**
 *
 *  @author Głodowski Michał S10552
 *
 */

package zad2;

// Choose initial file with projects.
def sourceData = new File("src/zad2/Projekty.txt")

// Choose file destined to hold information about large scale projects.
def largeScaleProjects = new File("src/zad2/ProjektyDuze.txt")
// If there is something in that file - clean it.
if (largeScaleProjects != null) {
	largeScaleProjects.text = ""
}
// Choose file destined to hold information about assignments to projects.
def assignments = new File("src/zad2/Programisci.txt")
// If there is something in that file - clean it.
if (assignments != null) {
	assignments.text = ""
}
// Define empty map of who belongs to which project.
def memberships = [:].withDefault { [] }
// For initial projects' file read it line by line.
def readLineByLine = sourceData.readLines()

readLineByLine.each {
	if (it != null) {
		// Separate elements as advised in schema.
		def formatData = it.split("\t")
		// For projects' names take first elements.
		def projects = formatData.take(1)
		// For members' who work on projects take everything, BUT projects' names.
		def members = formatData.drop(1)
		
		// Look for projects with more than three team members.
		if (members.size() > 3) {
			projects.each {
				// If there are any then toss them into large scale projects file. 
				largeScaleProjects.text += it + "\n"
			}
			// Inform about each new addition aka every large scale project found.
			println "New large scale project found: \t" + projects
			println "Updated 'ProjektyDuze.txt' with new information."
		}
		// Create reverse map with names as keys and projects as values.
		members.each {  
			if (memberships.containsKey(it) == true) {
				// Check if team member is already in new map. If they are then give them their projects.
				def isInProjects = memberships.get(it)
				isInProjects.add(projects) 
			} else {
				// If team member is not in the map add them with first found project they are a part of. Aka return first pair member : project.
				def projectsBox = [projects]
				memberships.put(it, projectsBox)
			}
		}
	} else {
		// If initial file is empty or data in that file violates expected schema inform about it.
		println "No data to analyze or data format differ from expected."
	}
}
// Forward new map to the file accordingly to the schema.
memberships.each { key, value ->
	def myProjects = ""
	// For every value add it as text with TAB beforehand.
	value.each {  
		myProjects += "\t" + it
	}
	// Add everything to the file. Key (team member name) first then all their values (assigned projects).
	assignments.text += key + myProjects.replaceAll("\\[", "").replaceAll("\\]", "") + "\n"
	// Inform about each addition in console.
	println "\nAssignment acknowledged! \t" + key + " : " + value
	println "Updated 'Programisci.txt' with new information."
	
}

