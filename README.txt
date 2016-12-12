Name : Sowmya Parameshwara
CWID : A20387836
email id : sparameshwara@hawk.iit.edu

=======================================================================================================================================
                                               * Algorithm for Cartesian Product *
=======================================================================================================================================

1) Read both input file data parallelly using 2 threads and stored them in a List<String>
2) Based on the tuple to be emitted in the output, I determine the index position of input one's list element
   and input two's list element, use these indexes to output the correct element into the file.

=======================================================================================================================================
                                              * Additional Points *
=======================================================================================================================================

1) This implementation handles duplicate elements in the table.
2) Tried using hashing, array manipulation, trees and brute force. This implementation however has better performance
   than all the remaining, hence submitting this.
3) Tested with 2 files having 20000 records each. It took around 80 seconds for processing it.

======================================================================================================================================

This is a java program. To test it with different input, please change "fileName1" and "fileName2" value in CartesianProductAlgorithm class.
