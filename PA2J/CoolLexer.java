/*
 *  The scanner definition for COOL.
 */
import java.lang.Boolean;
import java_cup.runtime.Symbol;


class CoolLexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

/*  Stuff enclosed in %{ %} is copied verbatim to the lexer class
 *  definition, all the extra variables/functions you want to use in the
 *  lexer actions should go here.  Don't remove or modify anything that
 *  was there initially.  */
    // Max size of string constants
    static int MAX_STR_CONST = 1025;
    // For assembling string constants
    StringBuffer string_buf = new StringBuffer();
    private int curr_lineno = 1;
    int get_curr_lineno() {
	return curr_lineno;
    }
    private AbstractSymbol filename;
    void set_filename(String fname) {
	filename = AbstractTable.stringtable.addString(fname);
    }
    AbstractSymbol curr_filename() {
	return filename;
    }

  private int comment_count = 0;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	CoolLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	CoolLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private CoolLexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

/*  Stuff enclosed in %init{ %init} is copied verbatim to the lexer
 *  class constructor, all the extra initialization you want to do should
 *  go here.  Don't remove or modify anything that was there initially. */
    // empty for now
	}

	private boolean yy_eof_done = false;
	private final int STRING = 2;
	private final int STRING_ERROR = 3;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		221,
		222,
		223
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NOT_ACCEPT,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NOT_ACCEPT,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NOT_ACCEPT,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NOT_ACCEPT,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NOT_ACCEPT,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NOT_ACCEPT,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NOT_ACCEPT,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NOT_ACCEPT,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NOT_ACCEPT,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NOT_ACCEPT,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NOT_ACCEPT,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NOT_ACCEPT,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NOT_ACCEPT,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NO_ANCHOR,
		/* 199 */ YY_NOT_ACCEPT,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NOT_ACCEPT,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NOT_ACCEPT,
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NOT_ACCEPT,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NOT_ACCEPT,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NOT_ACCEPT,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NOT_ACCEPT,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NOT_ACCEPT,
		/* 218 */ YY_NO_ANCHOR,
		/* 219 */ YY_NOT_ACCEPT,
		/* 220 */ YY_NOT_ACCEPT,
		/* 221 */ YY_NOT_ACCEPT,
		/* 222 */ YY_NOT_ACCEPT,
		/* 223 */ YY_NOT_ACCEPT,
		/* 224 */ YY_NOT_ACCEPT,
		/* 225 */ YY_NO_ANCHOR,
		/* 226 */ YY_NOT_ACCEPT,
		/* 227 */ YY_NOT_ACCEPT,
		/* 228 */ YY_NO_ANCHOR,
		/* 229 */ YY_NO_ANCHOR,
		/* 230 */ YY_NO_ANCHOR,
		/* 231 */ YY_NOT_ACCEPT,
		/* 232 */ YY_NOT_ACCEPT,
		/* 233 */ YY_NO_ANCHOR,
		/* 234 */ YY_NO_ANCHOR,
		/* 235 */ YY_NO_ANCHOR,
		/* 236 */ YY_NO_ANCHOR,
		/* 237 */ YY_NOT_ACCEPT,
		/* 238 */ YY_NO_ANCHOR,
		/* 239 */ YY_NO_ANCHOR,
		/* 240 */ YY_NO_ANCHOR,
		/* 241 */ YY_NO_ANCHOR,
		/* 242 */ YY_NO_ANCHOR,
		/* 243 */ YY_NO_ANCHOR,
		/* 244 */ YY_NO_ANCHOR,
		/* 245 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"62,57:8,63,60,63:2,61,57:18,63,57,56,57:5,4,5,12,10,6,11,9,13,35:10,7,1,15," +
"16,17,57,8,36,37,38,39,40,41,37,42,43,37:2,44,37,45,46,47,37,48,49,50,37,51" +
",52,37:3,53,58,57:2,55,57,21,59,18,34,26,29,54,25,23,54:2,19,54,24,31,32,54" +
",27,22,28,54,33,30,54:3,2,20,3,14,57:65409,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,246,
"0,1:4,2,1:6,3,4,1:2,5,6,1,7,8,9,1:2,10,1,11,1:4,12,1,13,1,14,1,15,16,1,16,1" +
",17,18,1:2,19,1:4,20,1:10,21,1,22,23,24,25,26,27,1,24,28,24,1,24,1:2,24,1,1" +
"6,1:2,24:2,1,24,29,30,31,32,33,34,35,36,9,37,9,24,9,24:2,9,38,39,24,17,40,9" +
",24,9,41,42,43,44,24,45,9:2,24:2,9,24,9:2,46,47,48,9:5,49,50,51,52,53,54,55" +
",56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,14,76,77,78,79" +
",80,81,82,83,84,85,86,87,88,89,90,91,92,15,93,94,95,96,97,98,99,100,101,102" +
",103,104,105,106,107,108,109,110,111,112,16,113,114,39,115,116,17,117,118,3" +
"8,119,120,121,122,123,124,125,126,40,127,19,128,129,130,131,21,132,133,134," +
"135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152")[0];

	private int yy_nxt[][] = unpackFromString(153,64,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,225,65,66:2,92,236,66,241,6" +
"6,243,113,244,126,245,66:2,20,21:2,67,21,93,114,21,127,135,138,141,144,21:2" +
",147,21,150,18,66,18,22,18:2,66,23,24,18,69,-1:76,25,-1:62,26,-1:57,27,-1:6" +
"9,28,-1:4,29,-1:64,30,-1:64,66,134,64,137,66:14,137,66:7,134,66:8,-1,66:2,-" +
"1:3,66,-1:39,20,-1:46,21:2,-1,21:35,-1:3,21,-1:64,23,69,-1,69,-1,26:59,-1:2" +
",26:2,-1:20,116,169,166,172,-1,231,175,-1,74,-1,36,178,-1,232,-1:2,169,-1:3" +
",175,-1,231,172,-1:2,178,-1:2,166,74,232,36,-1:31,231,-1:4,231,-1:16,231,-1" +
":41,39,-1:11,39,-1:14,39,-1:35,81,39,-1:11,39,-1:11,81,-1:2,39,-1:36,44,-1," +
"44,-1:26,44,-1:34,45,-1:5,45,-1:13,45,-1:43,107,-1,44,213,-1:2,45,215,-1:12" +
",45,-1:2,213,-1:4,215,44,-1:34,220,-1:7,220,-1:21,220,-1:14,51:55,-1,51,-1," +
"51,-1,51,-1,51,-1,224:55,62,224:3,63,-1,224:2,-1:20,154,227,157,-1:13,227,-" +
"1:12,157,-1:33,226,31,112,125,32,33,133,136,-1:2,70,-1,139,-1:4,112,-1:3,13" +
"6,70,133,32,226,33,139,-1:2,125,-1:32,66:2,-1,66:32,-1,66:2,-1:3,66,-1:22,2" +
"1,153,64,156,21:14,156,21:7,153,21:11,-1:3,21,-1:64,23,-1:64,69,-1,69,-1:18" +
",66:2,231,66:4,234,66:16,234,66:10,-1,66:2,-1:3,66,-1:16,49,-1:52,51:55,-1," +
"51,-1,51,60,51,-1,51,-1:60,63,-1:23,35,-1:7,74,-1:2,160,-1:14,160,-1:3,74,-" +
"1:14,55:23,56,55:3,57,58,55:29,59,60,-1,61,55,-1:18,66:2,94,66,146,66,72,66" +
":4,115,66:11,115,66:3,72,66:3,146,66:3,-1,66:2,-1:3,66,-1:22,21,233,237,21," +
"159,21:21,233,21:4,159,21:6,-1:3,21,-1:24,190,-1:4,231,-1:7,232,-1:8,231,-1" +
":8,232,-1:30,21:2,231,21:4,235,21:16,235,21:13,-1:3,21,-1:24,215,-1:6,215,-" +
"1:20,215,-1:35,213,-1:2,213,-1:19,213,-1:40,46,-1:2,219,-1:10,85,-1:4,85,-1" +
":3,219,-1:25,50,-1:78,157,-1,157,-1:26,157,-1:32,66:2,32,66:2,71,66:19,71,6" +
"6:9,-1,66:2,-1:3,66,-1:22,21:2,32,21:2,95,21:19,95,21:12,-1:3,21,-1:22,41,4" +
"2,43,-1,199,-1,82,-1,103,-1:4,202,39,-1:5,41,-1,103,-1:3,42,82,202,39,-1,19" +
"9,-1:34,181,184,-1:11,232,-1:2,184,-1:14,232,-1:30,66:2,34,66:8,73,66:11,73" +
",66:11,-1,66:2,-1:3,66,-1:22,21:2,94,21,162,21,96,21:4,128,21:11,128,21:3,9" +
"6,21:3,162,21:6,-1:3,21,-1:24,187,-1:2,172,-1:2,175,-1:13,175,-1:2,172,-1:3" +
"8,66:2,227,238,66:14,238,66:16,-1,66:2,-1:3,66,-1:22,21:2,90,21:5,165,21:4," +
"230,21:8,165,21:5,230,21:9,-1:3,21,-1:24,74,-1:7,74,-1,36,-1:19,74,-1,36,-1" +
":29,66:2,157,66,164,66:26,164,66:3,-1,66:2,-1:3,66,-1:22,21:2,142,21:5,168," +
"21:4,171,21:8,168,21:5,171,21:9,-1:3,21,-1:24,37,-1:7,76,-1:2,178,-1:14,178" +
",-1:3,76,-1:31,66:2,74,66:7,98,66:21,98,66:2,-1,66:2,-1:3,66,-1:22,21:2,34," +
"21:8,97,21:11,97,21:14,-1:3,21,-1:24,36,-1:7,76,-1,36,-1:19,76,-1,36,-1:29," +
"66:2,160,66:10,167,66:14,167,66:6,-1,66:2,-1:3,66,-1:22,21:2,151,21:10,239," +
"21:14,239,21:9,-1:3,21,-1:24,175,-1:5,175,-1:13,175,-1:41,66:2,232,66:12,17" +
"0,66:17,170,66,-1,66:2,-1:3,66,-1:22,21:2,145,21:4,174,21:16,174,21:13,-1:3" +
",21,-1:24,172,-1:2,172,-1:19,172,-1:38,66:2,36,66:9,75,66:21,75,-1,66:2,-1:" +
"3,66,-1:22,21:2,148,21:4,177,21:16,177,21:13,-1:3,21,-1:24,196,-1:10,196,-1" +
":14,196,-1:35,66:2,76,66:7,100,66:21,100,66:2,-1,66:2,-1:3,66,-1:22,21:2,22" +
"7,240,21:14,240,21:19,-1:3,21,-1:24,38,-1,199,-1:3,77,-1:13,77,-1:8,199,-1:" +
"32,66:2,184,176,66:14,176,66:16,-1,66:2,-1:3,66,-1:22,21:2,157,21,180,21:26" +
",180,21:6,-1:3,21,-1:24,77,-1:5,77,-1:13,77,-1:41,66:2,175,66:5,179,66:13,1" +
"79,66:12,-1,66:2,-1:3,66,-1:22,21:2,184,186,21:14,186,21:19,-1:3,21,-1:22,6" +
"6:2,172,66:2,182,66:19,182,66:9,-1,66:2,-1:3,66,-1:22,21:2,232,21:12,242,21" +
":17,242,21:4,-1:3,21,-1:24,40,-1,199,-1:3,79,-1:13,79,-1:8,199,-1:32,66:2,7" +
"7,66:5,101,66:13,101,66:12,-1,66:2,-1:3,66,-1:22,21:2,74,21:7,129,21:21,129" +
",21:5,-1:3,21,-1:24,79,-1:5,79,-1:13,79,-1:41,66:2,39,66:11,78,66:14,78,66:" +
"5,-1,66:2,-1:3,66,-1:22,21:2,36,21:9,99,21:21,99,21:3,-1:3,21,-1:22,41,-1,8" +
"0,-1,199,-1:15,41,-1:10,199,-1:32,66:2,202,66:10,191,66:14,191,66:6,-1,66:2" +
",-1:3,66,-1:22,21:2,76,21:7,117,21:21,117,21:5,-1:3,21,-1:23,205:2,-1:23,20" +
"5,-1:37,66:2,79,66:5,119,66:13,119,66:12,-1,66:2,-1:3,66,-1:22,21:2,175,21:" +
"5,195,21:13,195,21:15,-1:3,21,-1:24,82,-1:3,82,-1:20,82,-1:36,120,66,41,66:" +
"17,120,66:14,-1,66:2,-1:3,66,-1:22,21:2,172,21:2,198,21:19,198,21:12,-1:3,2" +
"1,-1:22,66:2,82,66:3,122,66:20,122,66:7,-1,66:2,-1:3,66,-1:22,21:2,77,21:5," +
"118,21:13,118,21:15,-1:3,21,-1:22,41,-1,104,-1:10,202,-1:6,41,-1:7,202,-1:3" +
"5,66,197,205,66:23,197,66:8,-1,66:2,-1:3,66,-1:22,21:2,79,21:5,130,21:13,13" +
"0,21:15,-1:3,21,-1:22,41,-1,41,-1:17,41,-1:43,66,105,81,66:23,105,66:8,-1,6" +
"6:2,-1:3,66,-1:22,131,21,41,21:17,131,21:17,-1:3,21,-1:23,205,106,-1:3,82,-" +
"1:19,205,82,-1:36,66:2,44,66,83,66:26,83,66:3,-1,66:2,-1:3,66,-1:22,21:2,39" +
",21:11,102,21:14,102,21:8,-1:3,21,-1:24,211,-1:5,208,-1:4,202,-1:8,208,-1:5" +
",202,-1:35,66:2,213,66:2,200,66:19,200,66:9,-1,66:2,-1:3,66,-1:22,21,121,81" +
",21:23,121,21:11,-1:3,21,-1:22,41,-1,79,-1:5,79,-1:11,41,-1,79,-1:41,66:2,2" +
"15,66:6,203,66:20,203,66:4,-1,66:2,-1:3,66,-1:22,21:2,82,21:3,132,21:20,132" +
",21:10,-1:3,21,-1:23,81:2,-1:23,81,-1:37,66:2,45,66:5,84,66:13,84,66:12,-1," +
"66:2,-1:3,66,-1:22,21,210,205,21:23,210,21:11,-1:3,21,-1:22,66:2,85,66:13,1" +
"09,66:4,109,66:13,-1,66:2,-1:3,66,-1:22,21:2,44,21,123,21:26,123,21:6,-1:3," +
"21,-1:22,66:2,219,66:2,206,66:19,206,66:9,-1,66:2,-1:3,66,-1:22,21:2,213,21" +
":2,212,21:19,212,21:12,-1:3,21,-1:22,66:2,220,66:7,209,66:21,209,66:2,-1,66" +
":2,-1:3,66,-1:22,21:2,215,21:6,214,21:20,214,21:7,-1:3,21,-1:22,66:2,47,66," +
"86,66:26,86,66:3,-1,66:2,-1:3,66,-1:22,21:2,45,21:5,108,21:13,108,21:15,-1:" +
"3,21,-1:24,217,-1:2,213,-1:3,215,-1:15,213,-1:4,215,-1:33,21:2,85,21:13,124" +
",21:4,124,21:16,-1:3,21,-1:24,85,-1:13,85,-1:4,85,-1:42,21:2,219,21:2,216,2" +
"1:19,216,21:12,-1:3,21,-1:24,219,-1:2,219,-1:19,219,-1:38,21:2,220,21:7,218" +
",21:21,218,21:5,-1:3,21,-1:22,21:2,47,21,110,21:26,110,21:6,-1:3,21,-1:24,4" +
"7,-1,47,-1:26,47,-1:14,1,48:3,87,48:7,111,48:47,23,68,48:2,1,51:55,52,51,91" +
",51,53,88,54,51,1,224:55,62,224:3,63,89,224:2,-1:18,66:2,90,66:5,140,66:4,1" +
"43,66:8,140,66:5,143,66:6,-1,66:2,-1:3,66,-1:24,163,227,166,-1:13,227,-1:12" +
",166,-1:34,199,-1,199,-1:26,199,-1:32,66:2,166,66,173,66:26,173,66:3,-1,66:" +
"2,-1:3,66,-1:22,66:2,196,66:10,185,66:14,185,66:6,-1,66:2,-1:3,66,-1:22,21:" +
"2,160,21:10,189,21:14,189,21:9,-1:3,21,-1:24,208,-1:5,208,-1:13,208,-1:43,2" +
"02,-1:10,202,-1:14,202,-1:35,21:2,166,21,183,21:26,183,21:6,-1:3,21,-1:22,6" +
"6:2,208,66:5,194,66:13,194,66:12,-1,66:2,-1:3,66,-1:22,21:2,208,21:5,207,21" +
":13,207,21:15,-1:3,21,-1:22,66:2,142,66:5,149,66:4,152,66:8,149,66:5,152,66" +
":6,-1,66:2,-1:3,66,-1:24,193,184,166,-1:13,184,-1:12,166,-1:32,66:2,199,66," +
"188,66:26,188,66:3,-1,66:2,-1:3,66,-1:22,21:2,196,21:10,192,21:14,192,21:9," +
"-1:3,21,-1:22,21:2,199,21,201,21:26,201,21:6,-1:3,21,-1:22,66,228,237,66,15" +
"5,66:21,228,66:4,155,66:3,-1,66:2,-1:3,66,-1:22,21:2,202,21:10,204,21:14,20" +
"4,21:9,-1:3,21,-1:22,66:2,145,66:4,158,66:16,158,66:10,-1,66:2,-1:3,66,-1:2" +
"2,66:2,148,66:4,161,66:16,161,66:10,-1,66:2,-1:3,66,-1:22,66:2,151,66:10,22" +
"9,66:14,229,66:6,-1,66:2,-1:3,66,-1:4");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

/*  Stuff enclosed in %eofval{ %eofval} specifies java code that is
 *  executed when end-of-file is reached.  If you use multiple lexical
 *  states and want to do something special if an EOF is encountered in
 *  one of those states, place your code in the switch statement.
 *  Ultimately, you should return the EOF symbol, or your lexer won't
 *  work.  */
    switch(yy_lexical_state) {
    case YYINITIAL:
	/* nothing special to do in the initial state */
	break;
	/* If necessary, add code for other states here, e.g: */
    case COMMENT:
        yybegin(YYINITIAL);
        return new Symbol(TokenConstants.ERROR, "EOF in comment");
    case STRING:
    case STRING_ERROR:
        yybegin(YYINITIAL);
        return new Symbol(TokenConstants.ERROR, "EOF in string constant");
    }
    return new Symbol(TokenConstants.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ return new Symbol(TokenConstants.SEMI);        }
					case -3:
						break;
					case 3:
						{ return new Symbol(TokenConstants.LBRACE);      }
					case -4:
						break;
					case 4:
						{ return new Symbol(TokenConstants.RBRACE);      }
					case -5:
						break;
					case 5:
						{ return new Symbol(TokenConstants.LPAREN);      }
					case -6:
						break;
					case 6:
						{ return new Symbol(TokenConstants.RPAREN);      }
					case -7:
						break;
					case 7:
						{ return new Symbol(TokenConstants.COMMA);       }
					case -8:
						break;
					case 8:
						{ return new Symbol(TokenConstants.COLON);       }
					case -9:
						break;
					case 9:
						{ return new Symbol(TokenConstants.AT);          }
					case -10:
						break;
					case 10:
						{ return new Symbol(TokenConstants.DOT);         }
					case -11:
						break;
					case 11:
						{ return new Symbol(TokenConstants.PLUS);        }
					case -12:
						break;
					case 12:
						{ return new Symbol(TokenConstants.MINUS);       }
					case -13:
						break;
					case 13:
						{ return new Symbol(TokenConstants.MULT);        }
					case -14:
						break;
					case 14:
						{ return new Symbol(TokenConstants.DIV);         }
					case -15:
						break;
					case 15:
						{ return new Symbol(TokenConstants.NEG);         }
					case -16:
						break;
					case 16:
						{ return new Symbol(TokenConstants.LT);          }
					case -17:
						break;
					case 17:
						{ return new Symbol(TokenConstants.EQ);          }
					case -18:
						break;
					case 18:
						{ return new Symbol(TokenConstants.ERROR, yytext()); }
					case -19:
						break;
					case 19:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -20:
						break;
					case 20:
						{ return new Symbol(TokenConstants.INT_CONST,
				      AbstractTable.inttable.addString(yytext())); }
					case -21:
						break;
					case 21:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -22:
						break;
					case 22:
						{ yybegin(STRING); string_buf.setLength(0); }
					case -23:
						break;
					case 23:
						{ curr_lineno = curr_lineno + 1; }
					case -24:
						break;
					case 24:
						{ }
					case -25:
						break;
					case 25:
						{ yybegin(COMMENT); comment_count = comment_count + 1; }
					case -26:
						break;
					case 26:
						{ }
					case -27:
						break;
					case 27:
						{ return new Symbol(TokenConstants.ERROR, "Unmatched *)"); }
					case -28:
						break;
					case 28:
						{ return new Symbol(TokenConstants.ASSIGN);      }
					case -29:
						break;
					case 29:
						{ return new Symbol(TokenConstants.LE);          }
					case -30:
						break;
					case 30:
						{ return new Symbol(TokenConstants.DARROW);      }
					case -31:
						break;
					case 31:
						{ return new Symbol(TokenConstants.IF);          }
					case -32:
						break;
					case 32:
						{ return new Symbol(TokenConstants.FI);          }
					case -33:
						break;
					case 33:
						{ return new Symbol(TokenConstants.IN);          }
					case -34:
						break;
					case 34:
						{ return new Symbol(TokenConstants.OF);          }
					case -35:
						break;
					case 35:
						{ return new Symbol(TokenConstants.LET);         }
					case -36:
						break;
					case 36:
						{ return new Symbol(TokenConstants.NEW);         }
					case -37:
						break;
					case 37:
						{ return new Symbol(TokenConstants.NOT);         }
					case -38:
						break;
					case 38:
						{ return new Symbol(TokenConstants.CASE);        }
					case -39:
						break;
					case 39:
						{ return new Symbol(TokenConstants.LOOP);        }
					case -40:
						break;
					case 40:
						{ return new Symbol(TokenConstants.ELSE);        }
					case -41:
						break;
					case 41:
						{ return new Symbol(TokenConstants.ESAC);        }
					case -42:
						break;
					case 42:
						{ return new Symbol(TokenConstants.POOL);        }
					case -43:
						break;
					case 43:
						{ return new Symbol(TokenConstants.THEN);        }
					case -44:
						break;
					case 44:
						{ return new Symbol(TokenConstants.CLASS);   }
					case -45:
						break;
					case 45:
						{ return new Symbol(TokenConstants.WHILE);       }
					case -46:
						break;
					case 46:
						{ return new Symbol(TokenConstants.ISVOID);      }
					case -47:
						break;
					case 47:
						{ return new Symbol(TokenConstants.INHERITS);    }
					case -48:
						break;
					case 48:
						{ }
					case -49:
						break;
					case 49:
						{ comment_count = comment_count + 1; }
					case -50:
						break;
					case 50:
						{
				    comment_count = comment_count - 1;
				    if (comment_count == 0) {
				      yybegin(YYINITIAL);
				    }
                                }
					case -51:
						break;
					case 51:
						{ string_buf.append(yytext()); }
					case -52:
						break;
					case 52:
						{ yybegin(YYINITIAL);
                                  if (string_buf.length() >= MAX_STR_CONST) {
                                    return new Symbol(TokenConstants.ERROR, "String constant too long");
                                  }
				  return new Symbol(TokenConstants.STR_CONST,
				      AbstractTable.stringtable.addString(string_buf.toString()));
                                }
					case -53:
						break;
					case 53:
						{ curr_lineno = curr_lineno + 1;
                                  yybegin(YYINITIAL);
                                  return new Symbol(TokenConstants.ERROR, "Unterminated string constant"); }
					case -54:
						break;
					case 54:
						{ yybegin(STRING_ERROR);
                                  return new Symbol(TokenConstants.ERROR, "String contains null character."); }
					case -55:
						break;
					case 55:
						{ string_buf.append(yytext().charAt(1)); }
					case -56:
						break;
					case 56:
						{ string_buf.append('\n'); }
					case -57:
						break;
					case 57:
						{ string_buf.append('\t'); }
					case -58:
						break;
					case 58:
						{ string_buf.append('\f'); }
					case -59:
						break;
					case 59:
						{ string_buf.append('\b'); }
					case -60:
						break;
					case 60:
						{ curr_lineno = curr_lineno + 1;
                                  string_buf.append("\n"); }
					case -61:
						break;
					case 61:
						{ yybegin(STRING_ERROR);
                                  return new Symbol(TokenConstants.ERROR, "String contains escaped null character."); }
					case -62:
						break;
					case 62:
						{ yybegin(YYINITIAL); }
					case -63:
						break;
					case 63:
						{ yybegin(YYINITIAL); }
					case -64:
						break;
					case 65:
						{ return new Symbol(TokenConstants.ERROR, yytext()); }
					case -65:
						break;
					case 66:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -66:
						break;
					case 67:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -67:
						break;
					case 68:
						{ curr_lineno = curr_lineno + 1; }
					case -68:
						break;
					case 69:
						{ }
					case -69:
						break;
					case 70:
						{ return new Symbol(TokenConstants.IF);          }
					case -70:
						break;
					case 71:
						{ return new Symbol(TokenConstants.FI);          }
					case -71:
						break;
					case 72:
						{ return new Symbol(TokenConstants.IN);          }
					case -72:
						break;
					case 73:
						{ return new Symbol(TokenConstants.OF);          }
					case -73:
						break;
					case 74:
						{ return new Symbol(TokenConstants.LET);         }
					case -74:
						break;
					case 75:
						{ return new Symbol(TokenConstants.NEW);         }
					case -75:
						break;
					case 76:
						{ return new Symbol(TokenConstants.NOT);         }
					case -76:
						break;
					case 77:
						{ return new Symbol(TokenConstants.CASE);        }
					case -77:
						break;
					case 78:
						{ return new Symbol(TokenConstants.LOOP);        }
					case -78:
						break;
					case 79:
						{ return new Symbol(TokenConstants.ELSE);        }
					case -79:
						break;
					case 80:
						{ return new Symbol(TokenConstants.ESAC);        }
					case -80:
						break;
					case 81:
						{ return new Symbol(TokenConstants.POOL);        }
					case -81:
						break;
					case 82:
						{ return new Symbol(TokenConstants.THEN);        }
					case -82:
						break;
					case 83:
						{ return new Symbol(TokenConstants.CLASS);   }
					case -83:
						break;
					case 84:
						{ return new Symbol(TokenConstants.WHILE);       }
					case -84:
						break;
					case 85:
						{ return new Symbol(TokenConstants.ISVOID);      }
					case -85:
						break;
					case 86:
						{ return new Symbol(TokenConstants.INHERITS);    }
					case -86:
						break;
					case 87:
						{ }
					case -87:
						break;
					case 88:
						{ string_buf.append(yytext()); }
					case -88:
						break;
					case 89:
						{ yybegin(YYINITIAL); }
					case -89:
						break;
					case 91:
						{ return new Symbol(TokenConstants.ERROR, yytext()); }
					case -90:
						break;
					case 92:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -91:
						break;
					case 93:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -92:
						break;
					case 94:
						{ return new Symbol(TokenConstants.IF);          }
					case -93:
						break;
					case 95:
						{ return new Symbol(TokenConstants.FI);          }
					case -94:
						break;
					case 96:
						{ return new Symbol(TokenConstants.IN);          }
					case -95:
						break;
					case 97:
						{ return new Symbol(TokenConstants.OF);          }
					case -96:
						break;
					case 98:
						{ return new Symbol(TokenConstants.LET);         }
					case -97:
						break;
					case 99:
						{ return new Symbol(TokenConstants.NEW);         }
					case -98:
						break;
					case 100:
						{ return new Symbol(TokenConstants.NOT);         }
					case -99:
						break;
					case 101:
						{ return new Symbol(TokenConstants.CASE);        }
					case -100:
						break;
					case 102:
						{ return new Symbol(TokenConstants.LOOP);        }
					case -101:
						break;
					case 103:
						{ return new Symbol(TokenConstants.ELSE);        }
					case -102:
						break;
					case 104:
						{ return new Symbol(TokenConstants.ESAC);        }
					case -103:
						break;
					case 105:
						{ return new Symbol(TokenConstants.POOL);        }
					case -104:
						break;
					case 106:
						{ return new Symbol(TokenConstants.THEN);        }
					case -105:
						break;
					case 107:
						{ return new Symbol(TokenConstants.CLASS);   }
					case -106:
						break;
					case 108:
						{ return new Symbol(TokenConstants.WHILE);       }
					case -107:
						break;
					case 109:
						{ return new Symbol(TokenConstants.ISVOID);      }
					case -108:
						break;
					case 110:
						{ return new Symbol(TokenConstants.INHERITS);    }
					case -109:
						break;
					case 111:
						{ }
					case -110:
						break;
					case 113:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -111:
						break;
					case 114:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -112:
						break;
					case 115:
						{ return new Symbol(TokenConstants.IF);          }
					case -113:
						break;
					case 116:
						{ return new Symbol(TokenConstants.LET);         }
					case -114:
						break;
					case 117:
						{ return new Symbol(TokenConstants.NOT);         }
					case -115:
						break;
					case 118:
						{ return new Symbol(TokenConstants.CASE);        }
					case -116:
						break;
					case 119:
						{ return new Symbol(TokenConstants.ELSE);        }
					case -117:
						break;
					case 120:
						{ return new Symbol(TokenConstants.ESAC);        }
					case -118:
						break;
					case 121:
						{ return new Symbol(TokenConstants.POOL);        }
					case -119:
						break;
					case 122:
						{ return new Symbol(TokenConstants.THEN);        }
					case -120:
						break;
					case 123:
						{ return new Symbol(TokenConstants.CLASS);   }
					case -121:
						break;
					case 124:
						{ return new Symbol(TokenConstants.ISVOID);      }
					case -122:
						break;
					case 126:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -123:
						break;
					case 127:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -124:
						break;
					case 128:
						{ return new Symbol(TokenConstants.IF);          }
					case -125:
						break;
					case 129:
						{ return new Symbol(TokenConstants.LET);         }
					case -126:
						break;
					case 130:
						{ return new Symbol(TokenConstants.ELSE);        }
					case -127:
						break;
					case 131:
						{ return new Symbol(TokenConstants.ESAC);        }
					case -128:
						break;
					case 132:
						{ return new Symbol(TokenConstants.THEN);        }
					case -129:
						break;
					case 134:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -130:
						break;
					case 135:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -131:
						break;
					case 137:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -132:
						break;
					case 138:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -133:
						break;
					case 140:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -134:
						break;
					case 141:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -135:
						break;
					case 143:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -136:
						break;
					case 144:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -137:
						break;
					case 146:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -138:
						break;
					case 147:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -139:
						break;
					case 149:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -140:
						break;
					case 150:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -141:
						break;
					case 152:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -142:
						break;
					case 153:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -143:
						break;
					case 155:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -144:
						break;
					case 156:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -145:
						break;
					case 158:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -146:
						break;
					case 159:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -147:
						break;
					case 161:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -148:
						break;
					case 162:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -149:
						break;
					case 164:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -150:
						break;
					case 165:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -151:
						break;
					case 167:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -152:
						break;
					case 168:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -153:
						break;
					case 170:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -154:
						break;
					case 171:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -155:
						break;
					case 173:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -156:
						break;
					case 174:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -157:
						break;
					case 176:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -158:
						break;
					case 177:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -159:
						break;
					case 179:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -160:
						break;
					case 180:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -161:
						break;
					case 182:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -162:
						break;
					case 183:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -163:
						break;
					case 185:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -164:
						break;
					case 186:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -165:
						break;
					case 188:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -166:
						break;
					case 189:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -167:
						break;
					case 191:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -168:
						break;
					case 192:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -169:
						break;
					case 194:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -170:
						break;
					case 195:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -171:
						break;
					case 197:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -172:
						break;
					case 198:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -173:
						break;
					case 200:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -174:
						break;
					case 201:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -175:
						break;
					case 203:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -176:
						break;
					case 204:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -177:
						break;
					case 206:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -178:
						break;
					case 207:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -179:
						break;
					case 209:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -180:
						break;
					case 210:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -181:
						break;
					case 212:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -182:
						break;
					case 214:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -183:
						break;
					case 216:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -184:
						break;
					case 218:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -185:
						break;
					case 225:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -186:
						break;
					case 228:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -187:
						break;
					case 229:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -188:
						break;
					case 230:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -189:
						break;
					case 233:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -190:
						break;
					case 234:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -191:
						break;
					case 235:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -192:
						break;
					case 236:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -193:
						break;
					case 238:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -194:
						break;
					case 239:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -195:
						break;
					case 240:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -196:
						break;
					case 241:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -197:
						break;
					case 242:
						{ return new Symbol(TokenConstants.TYPEID,
		                      AbstractTable.idtable.addString(yytext())); }
					case -198:
						break;
					case 243:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -199:
						break;
					case 244:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -200:
						break;
					case 245:
						{ if (yytext().toLowerCase().equals("true")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("True"));
                                    }
				  if (yytext().toLowerCase().equals("false")) {
				    return new Symbol(TokenConstants.BOOL_CONST,
				      new java.lang.Boolean("False"));
				  }
				  return new Symbol(TokenConstants.OBJECTID,
		                      AbstractTable.idtable.addString(yytext()));
				}
					case -201:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
