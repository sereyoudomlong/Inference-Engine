# Inference-Engine
This is a repo for assignment 2 of COS30019
# Student Details
Name: Sereyoudom Long
ID: 101942116
# Summary for this project
This project is to create an inference engine which can do Truth Table(TT) algortihm with all knowledge bases, Forward Chaining(FC) and Backward Chaining(BC) with Horn-Form Knowledge Bases.

- Example: <br />
  TELL:<br />
  p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;<br />
  ASK:<br />
  d<br />
  
  TT Output should be => YES: 3 (YES if d is true, NO if not, and 3 is the amount of propositional symbols entailed from KB that was found)<br />
  FC Output should be => YES: a, b, p2, p3, p1, d (YES if d is true, then follow by the order of which the program follow to find d)<br />
  BC Output should be => YES: p2, p3, p1, d (This is the reverse of FC which it follow backward from d in knowledge base)<br />
  
# Running the program

  
