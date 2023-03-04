/**
 *
 *  @author Głodowski Michał S10552
 *
 */

package zad3;

import groovy.io.*

// Choose directory to be searched.
def findHtmls = new File('src/zad3/DantesInferno')
def index = 1
// Travel thought the folders tree and find all ".html" files in it.
findHtmls.traverse(type: FileType.FILES, nameFilter: ~/.*\.html/){
	if (it != null) {
		// If ".html" file is found print index and path of this file then increase index for next result as expected in schema.
		println index + " " + it
		index++
	} else {
		// If no ".html" files have been found inform accordingly.
		println "No html files in the directory or any of it's subdirectories!"
	}
}
