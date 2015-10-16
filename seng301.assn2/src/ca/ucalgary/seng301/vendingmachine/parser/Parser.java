/* Generated By:JavaCC: Do not edit this line. Parser.java */
package ca.ucalgary.seng301.vendingmachine.parser;

import java.util.*;
import ca.ucalgary.seng301.vendingmachine.*;
import ca.ucalgary.seng301.vendingmachine.hardware.*;

@SuppressWarnings("all")
public class Parser implements ParserConstants {
  private boolean debug = false;

  public void setDebug(boolean flag) {
    debug = flag;
  }

  public Parser(String s) {
    this(new java.io.StringReader(s));
  }

  public boolean inputIsConsumed()
  {
    try
    {
      Token t = token_source.getNextToken();
      if (t.kind != ParserConstants.EOF || t.specialToken != null) return false;
    }
    catch (TokenMgrError e)
    {
      return false;
    }
    return true;
  }

  private IVendingMachineFactory vm = null;
  private ArrayList<Object> extraction = new ArrayList<Object>();
  private VendingMachineStoredContents teardown = null;

  public void register(IVendingMachineFactory vm) {
    this.vm = vm;
  }

  private boolean checkDelivery(int change, List<String> pops)
  {
    boolean result = true;

    if(extraction == null)
      return false;

        for(Object o: extraction)
        {
          if(o instanceof Coin)
            change -= ((Coin)o).getValue();
          else
          {
            String name = ((PopCan)o).getName();
            if(!(pops.contains(name))) {
                result = false;
                if(debug)
                  System.err.println("Failure: " + name + " has been returned where it should not have been");
        }
        else
          pops.remove(name);
          }
        }

        if(change != 0) {
          result = false;
          if(debug)
            System.err.println("Failure: " + change + " != 0");
    }

        if(!pops.isEmpty())
                for(String s : pops) {
                  result = false;
                  if(debug)
                    System.err.println("Failure: expected to find " + s);
                }

        return result;
  }

  private boolean checkTeardown(int change, int payments, List<String> pops) {
    boolean result = true;

        if(teardown == null) {
          if(debug)
            System.err.println("Failure: teardown is null");
          return false;
        }

    int totalCoins = 0;
    for(List<Coin> coins : teardown.unusedCoinsForChange) {
        for(Coin coin : coins) {
            totalCoins += coin.getValue();
        }
    }

    if(totalCoins != change) {
            result = false;
                if(debug)
                  System.err.println("Failure: change expected is " + change + " but was " + totalCoins);
    }

        totalCoins = 0;
        for(Coin coin : teardown.paymentCoinsInStorageBin)
            totalCoins += coin.getValue();

    if(totalCoins != payments) {
            result = false;
                if(debug)
                  System.err.println("Failure: payments expected is " + payments + " but was " + totalCoins);
    }

    for(List<PopCan> popCans : teardown.unsoldPopCans)
    {

        for(PopCan popCan : popCans) {
            String name = popCan.getName();
            if(!(pops.contains(name))) {
                    result = false;
                    if(debug)
                        System.err.println("Failure: " + name + " has been returned where it should not have been");
            }
            else
                pops.remove(name);
      }
    }


        if(!pops.isEmpty())
        {

                for(String s : pops) {
                  result = false;
                  if(debug)
                    System.err.println("Failure: expected to find " + s);
                }
}

        return result;
  }

  private void announceConstruct(ArrayList<Integer> coinKinds, int selectionButtonCount, int coinRackCapacity, int popCanRackCapacity, int receptacleCapacity) {
    vm.construct(coinKinds, selectionButtonCount, coinRackCapacity, popCanRackCapacity, receptacleCapacity);
  }

  private void announceConfigure(ArrayList<String> popNames, ArrayList<Integer> popCosts) {
    vm.configure(popNames, popCosts);
  }

  private void announceLoad(ArrayList<Integer> coinCounts, ArrayList<Integer> popCounts) {
    vm.load(coinCounts, popCounts);
  }

  private void announceUnload() {
    teardown = null;
    teardown = vm.unload();
  }

  private void announceExtract() {
    extraction.clear();
    extraction.addAll(vm.extract());
  }

  private void announcePress(int value) {
    vm.press(value);
  }

  private void announceInsert(int value) throws DisabledException {
    vm.insert(value);
  }

  final public void process(String path) throws ParseException, DisabledException {
  boolean res;
  int i = 0;
        System.err.println("Script: " + path);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONSTRUCT:
      case CONFIGURE:
      case EXTRACT:
      case PRESS:
      case INSERT:
      case LOAD:
      case UNLOAD:
      case CHECK_DELIVERY:
      case CHECK_TEARDOWN:
        ;
        break;
      default:
        break label_1;
      }
      res = Command();
    System.err.print("Command #" + i++ + ": ");
    if(res)
            System.err.println("PASS");
        else
            System.err.println("FAIL");
    }
    System.err.println();
    jj_consume_token(0);
  }

  final public boolean Command() throws ParseException, DisabledException {
  boolean res = true;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CONSTRUCT:
      Construct();
      break;
    case CONFIGURE:
      Configure();
      break;
    case EXTRACT:
      Extract();
      break;
    case PRESS:
      Press();
      break;
    case INSERT:
      Insert();
      break;
    case LOAD:
      Load();
      break;
    case UNLOAD:
      Unload();
      break;
    case CHECK_DELIVERY:
      res = CHECK_DELIVERY();
      break;
    case CHECK_TEARDOWN:
      res = CHECK_TEARDOWN();
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
  {if (true) return res;}
    throw new Error("Missing return statement in function");
  }

  final public void Construct() throws ParseException {
  Token ch;
  int selectionButtonCount, coinRackCapacity, popCanRackCapacity, receptacleCapacity;
  ArrayList<Integer> coinKinds = new ArrayList<Integer>();
    jj_consume_token(CONSTRUCT);
    jj_consume_token(LPAREN);
    ch = jj_consume_token(INTEGER_LITERAL);
    coinKinds.add(Integer.parseInt(ch.image));
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        break label_2;
      }
      jj_consume_token(COMMA);
      ch = jj_consume_token(INTEGER_LITERAL);
      coinKinds.add(Integer.parseInt(ch.image));
    }
    jj_consume_token(SEMICOLON);
    ch = jj_consume_token(INTEGER_LITERAL);
    selectionButtonCount = Integer.parseInt(ch.image);
    jj_consume_token(SEMICOLON);
    ch = jj_consume_token(INTEGER_LITERAL);
    coinRackCapacity = Integer.parseInt(ch.image);
    jj_consume_token(SEMICOLON);
    ch = jj_consume_token(INTEGER_LITERAL);
    popCanRackCapacity = Integer.parseInt(ch.image);
    jj_consume_token(SEMICOLON);
    ch = jj_consume_token(INTEGER_LITERAL);
    receptacleCapacity = Integer.parseInt(ch.image);
    jj_consume_token(RPAREN);
    announceConstruct(coinKinds, selectionButtonCount, coinRackCapacity, popCanRackCapacity, receptacleCapacity);
  }

  final public void Configure() throws ParseException {
  String name;
  Token cost;
  ArrayList<String> names = new ArrayList<String>();
  ArrayList<Integer> costs = new ArrayList<Integer>();
    jj_consume_token(CONFIGURE);
    jj_consume_token(LPAREN);
    name = String();
    names.add(name);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        break label_3;
      }
      jj_consume_token(COMMA);
      name = String();
      names.add(name);
    }
    jj_consume_token(SEMICOLON);
    cost = jj_consume_token(INTEGER_LITERAL);
    costs.add(Integer.parseInt(cost.image));
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        break label_4;
      }
      jj_consume_token(COMMA);
      cost = jj_consume_token(INTEGER_LITERAL);
      costs.add(Integer.parseInt(cost.image));
    }
    jj_consume_token(RPAREN);
    announceConfigure(names, costs);
  }

  final public void Load() throws ParseException {
  Token coinCount, popCount;
  ArrayList<Integer> coinCounts = new ArrayList<Integer>(), popCounts = new ArrayList<Integer>();
    jj_consume_token(LOAD);
    jj_consume_token(LPAREN);
    coinCount = jj_consume_token(INTEGER_LITERAL);
    coinCounts.add(Integer.parseInt(coinCount.image));
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        break label_5;
      }
      jj_consume_token(COMMA);
      coinCount = jj_consume_token(INTEGER_LITERAL);
      coinCounts.add(Integer.parseInt(coinCount.image));
    }
    jj_consume_token(SEMICOLON);
    popCount = jj_consume_token(INTEGER_LITERAL);
    popCounts.add(Integer.parseInt(popCount.image));
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        break label_6;
      }
      jj_consume_token(COMMA);
      popCount = jj_consume_token(INTEGER_LITERAL);
      popCounts.add(Integer.parseInt(popCount.image));
    }
    jj_consume_token(RPAREN);
    announceLoad(coinCounts, popCounts);
  }

  final public boolean CHECK_DELIVERY() throws ParseException {
  Token ch;
  String pop;
  int change;
  ArrayList<String> pops = new ArrayList<String>();
    jj_consume_token(CHECK_DELIVERY);
    jj_consume_token(LPAREN);
    ch = jj_consume_token(INTEGER_LITERAL);
    change = Integer.parseInt(ch.image);
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        break label_7;
      }
      jj_consume_token(COMMA);
      pop = String();
          pops.add(pop);
    }
    jj_consume_token(RPAREN);
    {if (true) return checkDelivery(change, pops);}
    throw new Error("Missing return statement in function");
  }

  final public boolean CHECK_TEARDOWN() throws ParseException {
  Token ch;
  String pop;
  int change, payments;
  ArrayList<String> pops = new ArrayList<String>();
    jj_consume_token(CHECK_TEARDOWN);
    jj_consume_token(LPAREN);
    ch = jj_consume_token(INTEGER_LITERAL);
    change = Integer.parseInt(ch.image);
    jj_consume_token(SEMICOLON);
    ch = jj_consume_token(INTEGER_LITERAL);
    payments = Integer.parseInt(ch.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SEMICOLON:
      jj_consume_token(SEMICOLON);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING_LITERAL:
        pop = String();
          pops.add(pop);
        label_8:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case COMMA:
            ;
            break;
          default:
            break label_8;
          }
          jj_consume_token(COMMA);
          pop = String();
          pops.add(pop);
        }
        break;
      default:
        ;
      }
      break;
    default:
      ;
    }
    jj_consume_token(RPAREN);
    {if (true) return checkTeardown(change, payments, pops);}
    throw new Error("Missing return statement in function");
  }

  final public void Extract() throws ParseException {
    jj_consume_token(EXTRACT);
    jj_consume_token(LPAREN);
    jj_consume_token(RPAREN);
    announceExtract();
  }

  final public void Press() throws ParseException {
  Token t;
    jj_consume_token(PRESS);
    jj_consume_token(LPAREN);
    t = jj_consume_token(INTEGER_LITERAL);
    jj_consume_token(RPAREN);
    announcePress(Integer.parseInt(t.image));
  }

  final public void Insert() throws ParseException, DisabledException {
  Token t;
    jj_consume_token(INSERT);
    jj_consume_token(LPAREN);
    t = jj_consume_token(INTEGER_LITERAL);
    jj_consume_token(RPAREN);
    announceInsert(Integer.parseInt(t.image));
  }

  final public void Unload() throws ParseException {
    jj_consume_token(UNLOAD);
    jj_consume_token(LPAREN);
    jj_consume_token(RPAREN);
    announceUnload();
  }

  final public String String() throws ParseException {
  Token t;
    t = jj_consume_token(STRING_LITERAL);
    String s = t.image;
    {if (true) return s.substring(1, s.length() - 1);}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      return token;
    }
    token = oldToken;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    Token errortok = token.next;
    int line = errortok.beginLine, column = errortok.beginColumn;
    String mess = (errortok.kind == 0) ? tokenImage[0] : errortok.image;
    return new ParseException("Parse error at line " + line + ", column " + column + ".  Encountered: " + mess);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}