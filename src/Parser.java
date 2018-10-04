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
      System.out.println("Enter program");
      accept(Token.LPAREN);
      parseSequence();
      parseState();
      accept(Token.RPAREN);
      System.out.println("Exit program");

//    while (currentToken.kind != Token.EOT)
//    {
//        if (currentToken.kind == Token.NOTHING) {
//          System.out.println("Line " + currentToken.line + ": wrong token " + currentToken.spelling);
//          if (currentToken.spelling == "?")
//          {
//            System.exit(3);
//          }
//        }
//        else {
//            System.out.println("Line: " + currentToken.line + ", spelling = [" + currentToken.spelling + "], kind = " + currentToken.kind);
//        }
//        currentToken = scanner.scan();
//    }
  }

  //Sequence --> "("Statements")".
  private void parseSequence(){
      System.out.println("Enter sequence");
      accept(Token.LPAREN);
      parseStatements();
      accept(Token.RPAREN);
      System.out.println("Exit sequence");
  }

  //Statements --> Stmt*
  private void parseStatements(){
      System.out.println("Enter statements");
      while(currentToken.kind != Token.RPAREN)
      {
          parseStmt();
      }
      System.out.println("Exit statements");
  }

  //Stmt --> "(" {NullStatement | Assignment | Conditional | Loop | Block}")".
  private void parseStmt(){
      System.out.println("Enter stmt");
      accept(Token.LPAREN);
      switch(currentToken.kind)
      {
          case Token.SKIP:
              parseNullStatement();
              break;
          case Token.ASSIGN:
              parseAssignment();
              break;
          case Token.CONDITIONAL:
              parseConditional();
              break;
          case Token.LOOP:
              parseLoop();
              break;
          case Token.BLOCK:
              parseBlock();
              break;
          default:
              new Error("Syntax error: Unexpected token in stmt", currentToken.line);
      }
      accept(Token.RPAREN);
      System.out.println("Exit stmt");
  }

  //State -->  "("Pairs")".
  private void parseState(){
      System.out.println("Enter state");
      accept(Token.LPAREN);
      parsePairs();
      accept(Token.RPAREN);
      System.out.println("Exit state");
  }

  //Pairs --> Pair*.
  private void parsePairs(){
      System.out.println("Enter pairs");
      while(currentToken.kind != Token.RPAREN)
      {
          parsePair();
      }
      System.out.println("Exit pairs");
  }

  //Pair --> "("Identifier Literal")".
  private void parsePair(){
      System.out.println("Enter pair");
      accept(Token.LPAREN);
      if (currentToken.kind == Token.IDENTIFIER)
      {
          acceptIt();
      }
      else
      {
          new Error("Syntax error: Unexpected token in pair", currentToken.line);
      }
      if (currentToken.kind == Token.LITERAL)
      {
          acceptIt();
      }
      else
      {
          new Error("Syntax error: Unexpected token in pair", currentToken.line);
      }
      accept(Token.RPAREN);
      System.out.println("Exit pair");
  }

  //NullStatement --> skip.
  private void parseNullStatement(){
      System.out.println("Enter null statement");
      accept(Token.SKIP);
      System.out.println("Exit null statement");
  }

  //Assignment --> "assign" Identifier Expressionn
  private void parseAssignment(){
      System.out.println("Enter assignment");
      accept(Token.ASSIGN);
      if (currentToken.kind == Token.IDENTIFIER)
      {
          acceptIt();
      }
      else
      {
          new Error("Syntax error: Unexpected token in statement", currentToken.line);
      }
      parseExpression();
      System.out.println("Exit assignment");
  }

  //Conditional --> "conditional" Expression Stmt Stmt.
  private void parseConditional(){
      System.out.println("Enter conditional");
      accept(Token.CONDITIONAL);
      parseExpression();
      parseStmt();
      parseStmt();
      System.out.println("Exit conditional");
  }

  //Loop --> "loop" Expression Stmt.
  private void parseLoop(){
      System.out.println("Enter loop");
      accept(Token.LOOP);
      parseExpression();
      parseStmt();
      System.out.println("Exit loop");
  }

  //Block --> "block" Statements.
  private void parseBlock(){
      System.out.println("Enter block");
      accept(Token.BLOCK);
      parseStatements();
      System.out.println("Exit block");
  }

  //Expression --> Identifier | Literal | "("Operation Expression Expression")".
  private void parseExpression(){
      System.out.println("Enter expression");
      if (currentToken.kind == Token.IDENTIFIER || currentToken.kind == Token.LITERAL)
      {
          acceptIt();
      }
      else
      {
          accept(Token.LPAREN);
          parseOperation();
          parseExpression();
          parseExpression();
          accept(Token.RPAREN);
      }
      System.out.println("Exit expression");
  }

  //Operation --> "+" |"-" | "*" | "/" | "<" | "<=" | ">" | ">=" | "=" | "!=" | "or" | "and".
  private void parseOperation(){
      System.out.println("Enter operation");
      if (currentToken.kind == Token.OPERATOR || currentToken.kind == Token.OR || currentToken.kind == Token.AND)
      {
          acceptIt();
      }
      else
      {
          new Error("Syntax error: Unexpected token in operation", currentToken.line);
      }
      System.out.println("Exit operation");

  }
}
