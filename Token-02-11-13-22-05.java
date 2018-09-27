/* You do not need a separate token (number) for each operator. All of them
   should be translated to one token (number) which is OPERATOR. See below

    Program --> Sequence State.
   Sequence --> (Statements ).
   Statements --> Statements Stmt | Stmt
   Stmt --> NullStatement | Assignment | Conditional | Loop | Block.
   State -->  (Pairs).
   Pairs --> Pairs Pair | Pair.
   Pair --> (Identifier Literal).
   NullStatement --> (skip).
   Assignment --> (assign Identifier Expression).
   Conditional --> (conditional Expression Stmt Stmt).
   Loop --> (loop Expression Stmt).
   Block --> (block Statements).
   Expression --> Identifier | Literal | (Operation Expression Expression).
  Operation --> + | - | * | / | < | <= | > | >= | = | != | or | and.
*/

public class Token{
  public byte kind;
  public String spelling;
  public int line;

  private final static String[] spellings = {
      "<identifier>", "<literal>", "assign", "conditional", "loop", "block",
      "skip", "and", "or"
   };


  public Token(byte kind, String spelling, int line){
    this.kind = kind;
    this.spelling = spelling;
    this.line = line;
    if(kind == IDENTIFIER)
      for(int k = ASSIGN; k <= OR; k++)
        if(spelling.equals(spellings[k])){
          this.kind = (byte)k;
          break;
        }
  }

  public final static byte
    IDENTIFIER =  0,
    LITERAL    =  1,
    ASSIGN     =  2,
    CONDITIONAL=  3,
    LOOP       =  4,
    BLOCK      =  5,
    SKIP       =  6,
    AND        =  7,
    OR         =  8,
    LPAREN     =  9,
    RPAREN     = 10,
    OPERATOR   = 11,
    NOTHING    = 12,       //Never happen but we need to return some thing in
                           //the switch in class Scannerwhen there is an error
    EOT        = 13;
}
