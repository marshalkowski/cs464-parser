/* Complete all the methods.
EBNF of Mini Language
Program" --> "("Sequence State")".
Sequence --> "("Statements")".
Statements --> Stmt*
Stmt --> "(" {NullStatement | Assignment | Conditional | Loop | Block}")".
State -->  "("Pairs")".
Pairs -->  Pair*.
Pair --> "("Identifier Literal")".
NullStatement --> "skip".
Assignment --> "assign" Identifier Expression.
Conditional --> "conditional" Expression Stmt Stmt.
Loop --> "loop" Expression Stmt.
Block --> "block" Statements.
Expression --> Identifier | Literal | "("Operation Expression Expression")".
Operation --> "+" |"-" | "*" | "/" | "<" | "<=" | ">" | ">=" | "=" | "!=" | "or" | "and".

Note: Treat Identifier and Literal as terminal symbols. Every symbol inside " and " is a terminal symbol. The rest are non terminals.

*/
import java.io.*;

public class Parser{
  private Token currentToken;
  Scanner scanner;

  private void accept(byte expectedKind) {
    if (currentToken.kind == expectedKind)
      currentToken = scanner.scan();
    else
      new Error("Syntax error: " + currentToken.spelling + " is not expected.",
                currentToken.line);
  }

  private void acceptIt() {
    currentToken = scanner.scan();
  }

  public void parse() {
    SourceFile sourceFile = new SourceFile();
    scanner = new Scanner(sourceFile.openFile());
    currentToken = scanner.scan();
    parseProgram();
    if (currentToken.kind != Token.EOT)
      new Error("Syntax error: Redundant characters at the end of program.",
                currentToken.line);
  }

  //Program" --> "("Sequence State")".
  private void parseProgram() {
    while (currentToken.kind != Token.EOT)
    {
      System.out.println("Line: " + currentToken.line + ", spelling = [" + currentToken.spelling + "], kind = " + currentToken.kind);

    }
  }

  //Sequence --> "("Statements")".
  private void parseSequence(){
  }

  //Statements --> Stmt*
  private void parseStatements(){
  }

  //Stmt --> "(" {NullStatement | Assignment | Conditional | Loop | Block}")".
  private void parseStmt(){
  }

  //State -->  "("Pairs")".
  private void parseState(){
  }

  //Pairs --> Pair*.
  private void parsePairs(){
  }

  //Pair --> "("Identifier Literal")".
  private void parsePair(){
  }

  //NullStatement --> skip.
  private void parseNullStatement(){
  }

  //Assignment --> "assign" Identifier Expression.
  private void parseAssignment(){
  }

  //Conditional --> "conditional" Expression Stmt Stmt.
  private void parseConditional(){
  }

  //Loop --> "loop" Expression Stmt.
  private void parseLoop(){
  }

  //Block --> "block" Statements.
  private void parseBlock(){
  }

  //Expression --> Identifier | Literal | "("Operation Expression Expression")".
  private void parseExpression(){
  }

  //Operation --> "+" |"-" | "*" | "/" | "<" | "<=" | ">" | ">=" | "=" | "!=" | "or" | "and".
  private void parseOperation(){
  }
}
