package compiler;

//  ************** REQUIRES JAVA 21 or later! (https://adoptium.net/) ************** //

import java.util.logging.Logger;

/*
 Important: Understanding the relationship between the comments below (the grammar) and the code
 below is critical to understanding how to implement a recursive descent parser!

 GRAMMAR FOR PROGRAM 1:

    <PROGRAM> ;;= <STMT_LIST> $$

    <STMT_LIST> ;;= <STMT> <STMT_LIST> | e

    <STMT> ;;= <READ_STMT> | <WRITE_STMT> | <VAR_DECL> | <LET_STMT> | <FUN_CALL> | <IF> | <DEF> | <FOR>

    <READ_STMT> ;;= 'read' id
    <WRITE_STMT> ;;= 'write' <EXPR>
    <LET_STMT> ;;= 'let' id <LET_TAIL>
    <VAR_DECL> ;;= 'var' id
    <FUN_CALL> ;;= id '(' <ARG_LIST> ')'

    <LET_TAIL> ;;= '=' <EXPR> | '<-' <FUN_CALL>

    <ARG_LIST> ;;= <EXPR> <ARGS_TAIL> | e
    <ARGS_TAIL> ;;= ',' <ARG_LIST> | e

    <EXPR> ;;= <TERM> <TERM_TAIL> 

    <TERM> ;;= <FACTOR> <FACTOR_TAIL>
    <TERM_TAIL> ;;= <ADD_OP> <TERM> <TERM_TAIL> | e

    <FACTOR> ;;= '-' <FACTOR> | '(' <EXPR> ')' | id | num
    <FACTOR_TAIL> ;;= <MULT_OP> <FACTOR> <FACTOR_TAIL> | e
    
    <ID_LIST> ;;= id <ID_LIST_TAIL> | e
    <ID_LIST_TAIL> ;;= ',' id <ID_LIST_TAIL> | e

    <ADD_OP> ;;= '-' | '+'
    <MULT_OP> ;;= '*' | '/'

    <CONDITION> ;;= <EXPR> <REL_OPER> <EXPR>
    <REL_OPER> ;;= '>' | '<' | '==' | '=<' | '>=' | '='

    <IF> ;;= 
            'if' '(' <CONDITION> ')' '{' 
                <STMT_LIST>
            '}' 'else' '{'
                <STMT_LIST>
            '}'

    <DEF> ;;=
            'def' id '(' <ID_LIST> ')' '{' <STMT_LIST> 'return' <EXPR> '}'

    <FOR> ;;=
            'for' '(' <VAR_DECL> ';' <CONDITION> ';' <LET_STMT> ')' '{'
                <STMT_LIST>
            '}'

    TB-NOTE!!! Anytime the 'let' word is invoked, check to make sure the next value (x) has already been placed into the tokenset by an earlier statement (in the form of 'var x').
    Note: This example violates the Java "coding conventions" that method names should be all lower case.
          but this is done to make the code more readable by exactly matching the names of grammar rules.
==================================================================================================================

NOTE: The ***PARSER_README.MD*** file has complete information, but here are some highlights:

 1. Each method implements a production rule.

 2. Each production-implementing method starts by adding itself to the tree via the codeGenerator.
    See the code below for examples of usage.

 3. The Code Generator implements:

       addNonTerminal(parentNode, displayName)
            — Adds a non-terminal node to the tree and returns the node that was added.

       addTerminal(parentNode, token, displayName)
            — Adds a terminal node to the tree and returns the node that was added.

       addEpsilon(parentNode, displayName)
            — Adds an empty node to the tree and returns the node that was added.

       syntaxError(parentNode, errorMessage)
            — Throws a ParseException with the given message and adds exception to the tree.

  4. The Lexical Analyzer Class implements:

       getCurrentToken()
            — Returns the current token.

       getCurrentLexeme()
            — Returns the current string that maps to the token.

       advanceToken()
            — Advances to the next token by setting the "current token" to the "next" token.
              If the end of the token list is reached, the current token is "$$" (end of file).

  5. The sample already includes the following methods which can be used WITHOUT modification:

       EPSILON(TreeNode parentNode)
            — Implements the epsilon production rule.
              (See Textbook Section 2.3.1)

       MATCH(Token token, TreeNode parentNode)
            — Matches the current token with the expected token.
              (See Textbook Section 2.3.1)
 */

class Parser {

    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    // !!!!!!!!!!!!!!!!!!! CODE MODIFICATIONS SHOULD BE MADE BELOW THIS LINE
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // ///////////////////////////////////////////////////////////////////////////////////////////////////

    // <PROGRAM> ;;= <STMT_LIST> $$
    // NOTE: The name of this method should not change unless you also change the
    // invocation from "analyze" below.
    private void PROGRAM(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<PROGRAM>");

        // Invoke the first rule.
        STMT_LIST(thisNode);

        // Match the end of file marker.
        MATCH(thisNode, TokenSet.$$);
        System.out.println("Variables caught: " + TokenSet.variList);
    }

    // <STMT_LIST> ;;= <STMT> <STMT_LIST> | e
    private void STMT_LIST(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<STMT_LIST>");

        // Invoke the rules for the statement.

        if (lexer.getCurrentToken() == TokenSet.STMT_KEYS) {
            STMT(thisNode);
            STMT_LIST(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.CONDITIONAL) {
            STMT(thisNode);
            STMT_LIST(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.SYSOUT) {
            MATCH(thisNode, TokenSet.SYSOUT);

        } else {
            EPSILON(thisNode);
        }
    }

    // <STMT> ;;= <READ_STMT> | <WRITE_STMT> | <VAR_DECL> | <LET_STMT> | <FUN_CALL>
    private void STMT(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<STMT>");

        // Check for a conjunction.
        if (lexer.getCurrentToken() == TokenSet.STMT_KEYS && lexer.getCurrentLexeme().equals("read")) {
            READ_STMT(thisNode);

        } else if (lexer.getCurrentToken() == TokenSet.STMT_KEYS && lexer.getCurrentLexeme().equals("write")) {
            WRITE_STMT(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.STMT_KEYS && lexer.getCurrentLexeme().equals("var")) {
            VAR_DECL(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.STMT_KEYS && lexer.getCurrentLexeme().equals("let")) {
            LET_STMT(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.UNIDENTIFIED_TOKEN) {
            FUN_CALL(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.CONDITIONAL && lexer.getCurrentLexeme().equals("if")) {
            IF(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.CONDITIONAL && lexer.getCurrentLexeme().equals("for")) {
            FOR(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.CONDITIONAL && lexer.getCurrentLexeme().equals("def")) {
            DEF(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.CONDITIONAL && lexer.getCurrentLexeme().equals("return")) {
            MATCH(thisNode, TokenSet.CONDITIONAL);

        }
    }

    // <READ_STMT> ;;= 'read' id
    private void READ_STMT(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<READ_STMT>");
        MATCH(thisNode, TokenSet.STMT_KEYS);

        MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);
    }

    // <WRITE_STMT> ;;= 'write' <EXPR>
    private void WRITE_STMT(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<WRITE_STMT>");
        MATCH(thisNode, TokenSet.STMT_KEYS);

        EXPR(thisNode);
    }

    // <LET_STMT> ;;= 'let' id <LET_TAIL>
    private void LET_STMT(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<LET_STMT>");

        if (lexer.getCurrentToken() == TokenSet.STMT_KEYS && lexer.getCurrentLexeme().equals("let")) {
            MATCH(thisNode, TokenSet.STMT_KEYS);

            if (TokenSet.variList.contains(lexer.getCurrentLexeme())) {
                MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);
                LET_TAIL(thisNode);
            } else {
                codeGenerator.syntaxError(parentNode,
                        ("Variable '%s' already exists within the function.").formatted(lexer.getCurrentLexeme()));
            }
        } else {

            if (TokenSet.varList.contains(lexer.getCurrentToken())) {
                LET_TAIL(thisNode);
            } else if (TokenSet.variList.contains(lexer.getCurrentLexeme())) {
                LET_TAIL(thisNode);
            }

        }

    }

    // <NUMBER> ;;= (0...9)*
    private void NUMBER(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<NUMBER>");

        while (lexer.getCurrentToken() == TokenSet.NUMBER) {
            MATCH(thisNode, TokenSet.NUMBER);
        }

    }

    // <VAR_DECL> ;;= 'var' id
    private void VAR_DECL(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<VAR_DECL>");

        MATCH(thisNode, TokenSet.STMT_KEYS);

        if (TokenSet.variList.contains(lexer.getCurrentLexeme())) {
            codeGenerator.syntaxError(parentNode,
                    ("Variable '%s' already exists within the function.").formatted(lexer.getCurrentLexeme()));
        } else {
            TokenSet.varList.add(lexer.getCurrentToken());
            // OR
            TokenSet.variList.add(lexer.getCurrentLexeme());
        }

        MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);

        if (lexer.getCurrentToken() == TokenSet.CONJUNCTION) {
            MATCH(thisNode, TokenSet.CONJUNCTION);
        } else if (lexer.getCurrentToken() == TokenSet.EQUAL) {
            MATCH(thisNode, TokenSet.EQUAL);
            NUMBER(thisNode);
            MATCH(thisNode, TokenSet.CONJUNCTION);
        }

    }

    // <FUN_CALL> ;;= id '(' <ARG_LIST> ')'
    private void FUN_CALL(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<FUN_CALL>");
        if (TokenSet.variList.contains(lexer.getCurrentLexeme())) {
            MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);

            MATCH(thisNode, TokenSet.PAREN);
            ARG_LIST(thisNode);
            MATCH(thisNode, TokenSet.PAREN);
        } else {
            codeGenerator.syntaxError(parentNode,
                    ("Function '%s' does not exist within the function.").formatted(lexer.getCurrentLexeme()));
        }

    }

    // <LET_TAIL> ;;= '=' <EXPR> | '<-' <FUN_CALL>
    private void LET_TAIL(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<LET_TAIL>");

        if (lexer.getCurrentToken() == TokenSet.EQUAL) {
            MATCH(thisNode, TokenSet.EQUAL);
            EXPR(thisNode);

        } else if (lexer.getCurrentToken() == TokenSet.ARROW) {
            MATCH(thisNode, TokenSet.ARROW);
            FUN_CALL(thisNode);
        }

    }

    // <ARG_LIST> ;;= <EXPR> <ARGS_TAIL> | e
    private void ARG_LIST(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<ARG_LIST>");

        // Check for anything.
        if (lexer.getCurrentToken() != TokenSet.$$) {
            EXPR(thisNode);
            ARGS_TAIL(thisNode);
        } else {
            EPSILON(thisNode);
        }
    }

    // <ARGS_TAIL> ;;= ',' <ARG_LIST> | e
    private void ARGS_TAIL(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<ARGS_TAIL>");

        // Check for a comma.
        if (lexer.getCurrentToken() == TokenSet.LIST_SEPARATOR) {
            ARG_LIST(thisNode);

        } else {
            EPSILON(thisNode);
        }
    }

    // <EXPR> ;;= <TERM> <TERM_TAIL>
    private void EXPR(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<EXPR>");

        TERM(thisNode);
        TERM_TAIL(thisNode);

    }

    // <TERM> ;;= <FACTOR> <FACTOR_TAIL>
    private void TERM(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<TERM>");

        FACTOR(thisNode);
        FACTOR_TAIL(thisNode);

    }

    // <TERM_TAIL> ;;= <ADD_OP> <TERM> <TERM_TAIL> | e
    private void TERM_TAIL(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<TERM_TAIL>");

        if (lexer.getCurrentToken() == TokenSet.ADD_OP) {
            MATCH(thisNode, TokenSet.ADD_OP);
            TERM(thisNode);
            TERM_TAIL(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.CONJUNCTION && lexer.getCurrentLexeme().equals(";")) {
            MATCH(thisNode, TokenSet.CONJUNCTION);
        } else if (lexer.getCurrentToken() == TokenSet.LIST_SEPARATOR) {
            MATCH(thisNode, TokenSet.LIST_SEPARATOR);
            ARG_LIST(thisNode);
        } else {
            EPSILON(thisNode);
        }

    }

    // <FACTOR> ;;= '-' <FACTOR> | '(' <EXPR> ')' | id | num
    private void FACTOR(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<FACTOR>");

        if (lexer.getCurrentToken() == TokenSet.ADD_OP) {
            MATCH(thisNode, TokenSet.ADD_OP);
            FACTOR(thisNode);
        } else if (lexer.getCurrentToken() == TokenSet.PAREN) {
            MATCH(thisNode, TokenSet.PAREN);
            EXPR(thisNode);
            MATCH(thisNode, TokenSet.PAREN);
        } else if (lexer.getCurrentToken() == TokenSet.UNIDENTIFIED_TOKEN
                && TokenSet.variList.contains(lexer.getCurrentLexeme())) {
            MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);
        } else if (lexer.getCurrentToken() == TokenSet.NUMBER) {
            MATCH(thisNode, TokenSet.NUMBER);
        }

    }

    // <FACTOR_TAIL> ;;= <MULT_OP> <FACTOR> <FACTOR_TAIL> | e
    private void FACTOR_TAIL(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<FACTOR_TAIL>");

        if (lexer.getCurrentToken() == TokenSet.MULT_OP) {
            MATCH(thisNode, TokenSet.MULT_OP);
            FACTOR(thisNode);
            FACTOR_TAIL(thisNode);
        } else {
            EPSILON(thisNode);
        }

    }

    // <ID_LIST> ;;= id <ID_LIST_TAIL> | e
    private void ID_LIST(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<ID_LIST>");

        if (lexer.getCurrentToken() == TokenSet.UNIDENTIFIED_TOKEN) {
            if (TokenSet.variList.contains(lexer.getCurrentLexeme())) {
                System.err.println("Syntax Error: Token already included. If defining a function, disregard.");

            } else {
                TokenSet.varList.add(lexer.getCurrentToken());
                // OR
                TokenSet.variList.add(lexer.getCurrentLexeme());
            }

            MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);
            ID_LIST_TAIL(thisNode);
        } else {
            EPSILON(thisNode);
        }

    }

    // <ID_LIST_TAIL> ;;= ',' id <ID_LIST_TAIL> | e
    private void ID_LIST_TAIL(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<ID_LIST_TAIL>");

        if (lexer.getCurrentToken() == TokenSet.LIST_SEPARATOR) {
            MATCH(thisNode, TokenSet.LIST_SEPARATOR);
            if (TokenSet.variList.contains(lexer.getCurrentLexeme())) {
                System.err.println("Syntax Error: Token already included. If defining a function, disregard.");

            } else {
                TokenSet.varList.add(lexer.getCurrentToken());
                // OR
                TokenSet.variList.add(lexer.getCurrentLexeme());
            }

            MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);
            ID_LIST_TAIL(thisNode);
        } else {
            EPSILON(thisNode);
        }

    }

    // <CONDITION> ;;= <EXPR> <REL_OPER> <EXPR>
    private void CONDITION(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<CONDITION>");

        EXPR(thisNode);
        MATCH(parentNode, TokenSet.REL_OPER);
        EXPR(thisNode);

    }
    /*
     * <IF> ;;=
     * 'if' '(' <CONDITION> ')' '{'
     * <STMT_LIST>
     * '}' 'else' '{'
     * <STMT_LIST>
     * '}'
     */

    private void IF(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<IF>");
        // Start of if including coditions to operate by
        if (lexer.getCurrentToken() == TokenSet.CONDITIONAL) {
            MATCH(thisNode, TokenSet.CONDITIONAL);

            MATCH(thisNode, TokenSet.PAREN);
            CONDITION(thisNode);
            MATCH(thisNode, TokenSet.PAREN);
            // Start of { <STMT_LIST> }
            if (lexer.getCurrentToken() == TokenSet.PAREN) {

                MATCH(thisNode, TokenSet.PAREN);
                STMT_LIST(thisNode);
                while (lexer.getCurrentToken() == TokenSet.CONDITIONAL) {
                    STMT_LIST(thisNode);
                }
                if (lexer.getCurrentToken() == TokenSet.CONJUNCTION) {
                    MATCH(thisNode, TokenSet.CONJUNCTION);
                }
                MATCH(thisNode, TokenSet.PAREN);
            }
            // Start of else { <STMT_LIST> }
            if (lexer.getCurrentToken() == TokenSet.CONDITIONAL) {
                MATCH(thisNode, TokenSet.CONDITIONAL);
                MATCH(thisNode, TokenSet.PAREN);
                STMT_LIST(thisNode);
                while (lexer.getCurrentToken() == TokenSet.CONDITIONAL) {
                    STMT_LIST(thisNode);
                }
                if (lexer.getCurrentToken() == TokenSet.CONJUNCTION) {
                    MATCH(thisNode, TokenSet.CONJUNCTION);
                }
                MATCH(thisNode, TokenSet.PAREN);

            }

        }

        if (lexer.getCurrentToken() == TokenSet.CONDITIONAL) {
            MATCH(thisNode, TokenSet.CONDITIONAL);

            MATCH(thisNode, TokenSet.PAREN);
            if (lexer.getCurrentToken() == TokenSet.UNIDENTIFIED_TOKEN) {
                CONDITION(thisNode);
                MATCH(thisNode, TokenSet.PAREN);
                MATCH(thisNode, TokenSet.PAREN);

            }
            STMT_LIST(thisNode);
            while (lexer.getCurrentToken() == TokenSet.CONDITIONAL) {
                STMT_LIST(thisNode);
            }
            if (lexer.getCurrentToken() == TokenSet.CONJUNCTION) {
                MATCH(thisNode, TokenSet.CONJUNCTION);
            }
            MATCH(thisNode, TokenSet.PAREN);

        }
    }

    /*
     * <DEF> ;;=
     * 'def' id '(' <ID_LIST> ')' '{' <STMT_LIST> 'return' <EXPR> '}'
     */
    private void DEF(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<DEF>");

        // Start of def including initial function collection (to 'symbol table')
        MATCH(thisNode, TokenSet.CONDITIONAL);
        if (TokenSet.variList.contains(lexer.getCurrentLexeme())) {
            codeGenerator.syntaxError(parentNode,
                    ("Variable '%s' already exists within the function.").formatted(lexer.getCurrentLexeme()));
        } else {
            TokenSet.varList.add(lexer.getCurrentToken());
            // OR
            TokenSet.variList.add(lexer.getCurrentLexeme());
        }
        MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);
        MATCH(thisNode, TokenSet.PAREN);

        // Start of <ID_LIST> and ')'
        ID_LIST(thisNode);
        MATCH(thisNode, TokenSet.PAREN);

        MATCH(thisNode, TokenSet.PAREN);
        STMT_LIST(thisNode);

        // self explanatory + 'return' is apart of <STMT_LIST>
        EXPR(thisNode);

        MATCH(thisNode, TokenSet.PAREN);
    }

    /*
     * <FOR> ;;=
     * 'for' '(' <VAR_DECL> ';' <CONDITION> ';' <LET_STMT> ')' '{'
     * <STMT_LIST>
     * '}'
     */
    private void FOR(final TreeNode parentNode) throws ParseException {
        final TreeNode thisNode = codeGenerator.addNonTerminal(parentNode, "<FOR>");

        // Start of for including ( <VAR_DECL> ; <CONDITION> ; <LET_STMT>)
        MATCH(thisNode, TokenSet.CONDITIONAL);

        MATCH(thisNode, TokenSet.PAREN);
        VAR_DECL(thisNode);
        if (lexer.getCurrentToken() == TokenSet.EQUAL) {
            MATCH(thisNode, TokenSet.EQUAL);
            EXPR(thisNode);

        } else if (lexer.getCurrentToken() == TokenSet.CONJUNCTION) {

        }
        CONDITION(thisNode);
        LET_STMT(thisNode);
        MATCH(thisNode, TokenSet.PAREN);

        // Yes, all the rest of this is just the { <STMT_LIST> }
        MATCH(thisNode, TokenSet.PAREN);
        if (lexer.getCurrentToken() == TokenSet.SYSOUT) {
            STMT_LIST(thisNode);
            while (lexer.getCurrentToken() == TokenSet.CONDITIONAL) {
                STMT_LIST(thisNode);
            }
            MATCH(thisNode, TokenSet.PAREN);
            MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);
            MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);
            MATCH(thisNode, TokenSet.UNIDENTIFIED_TOKEN);
            MATCH(thisNode, TokenSet.PAREN);
            MATCH(thisNode, TokenSet.CONJUNCTION);
        } else if (lexer.getCurrentToken() == TokenSet.STMT_KEYS) {

            while (lexer.getCurrentToken() == TokenSet.STMT_KEYS)
                STMT_LIST(thisNode);

        }
        if (lexer.getCurrentToken() == TokenSet.CONJUNCTION) {
            MATCH(thisNode, TokenSet.CONJUNCTION);
        }

        MATCH(thisNode, TokenSet.PAREN);

    }

    /// //////////////////////////////////////////////////////////////////////////////////

    /*
     * THIS CAN BE USED AS IS! Though you may modify it to suit your needs.
     *
     * Add an EPSILON terminal node (result of an Epsilon Production) to the parse
     * tree.
     * Mainly, this is just done for better visualizing the complete parse tree.
     *
     * @param parentNode The parent of the terminal node.
     */
    private void EPSILON(final TreeNode parentNode) {
        codeGenerator.addEpsilon(parentNode);
    }

    /*
     * THIS CAN BE USED AS IS! Though you may modify it to suit your needs.
     *
     * Match the current token with the expected token.
     * If they match, add the token to the parse tree, otherwise throw an exception.
     *
     * @param currentNode The current terminal node.
     * 
     * @param expectedToken The token to be matched.
     * 
     * @throws ParseException Thrown if the token does not match the expected token.
     */
    private void MATCH(final TreeNode currentNode, final TokenSet expectedToken) throws ParseException {
        // Get the current token and lexeme.
        var currentToken = lexer.getCurrentToken();
        var currentLexeme = lexer.getCurrentLexeme();

        // Check if the current token matches the expected token.
        if (currentToken == expectedToken) {
            codeGenerator.addTerminal(currentNode, currentToken, currentLexeme);
            lexer.advanceToken();
        } else {
            // If the current token does not match the expected token, throw an exception.
            var errorMessage = "SYNTAX ERROR: Expected '%s'\nbut found '%s' (%s)."
                    .formatted(expectedToken, currentLexeme, currentToken);
            codeGenerator.syntaxError(currentNode, errorMessage);
        }
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////////
    // !!!!!!!!!!!!!!!!!!! CODE MODIFICATIONS SHOULD BE MADE ABOVE THIS LINE
    // !!!!!!!!!!!!!!!!!!!!!!!!!
    // ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * THIS IS INVOKED DIRECTLY FROM MAIN AND SHOULD NOT BE MODIFIED.
     *
     * @param lexer         The TokenSet Object
     * @param codeGenerator The CodeGenerator Object
     */
    Parser(LexicalAnalyzer lexer, CodeGenerator codeGenerator) {
        this.lexer = lexer;
        this.codeGenerator = codeGenerator;

        // !!!! Change this to automatically prompt to see the Open WebGraphViz dialog
        // or not. !!!!
        MAIN.PROMPT_FOR_GRAPHVIZ = true;
    }

    /**
     * THIS IS INVOKED DIRECTLY FROM MAIN AND SHOULD NOT BE MODIFIED.
     * <p>
     * Since the "Compiler" portion of the code knows nothing about the start rule,
     * the "analyze" method must invoke the start rule.
     *
     * @param treeRoot The tree root.
     */
    public void analyze(TreeNode treeRoot) {
        try {
            // THIS IS OUR START RULE
            PROGRAM(treeRoot);
        } catch (ParseException ex) {
            final String msg = String.format("%s\n", ex.getMessage());
            Logger.getAnonymousLogger().severe(msg);
        }
    }

    // The lexer, which will provide the tokens
    private final LexicalAnalyzer lexer;

    // The "code generator"
    private final CodeGenerator codeGenerator;
}
