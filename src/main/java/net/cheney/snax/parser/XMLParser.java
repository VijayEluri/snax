package net.cheney.snax.parser;

import javax.annotation.Nonnull;

import net.cheney.snax.model.Document;

public final class XMLParser {
	
	private State state = State.CHARACTERS;
	
	private int offset, limit = 0;

	private CharSequence sequence;
	
	private NodeBuilder builder;

	public Document document() {
		return new Document(builder.contents());
	}

	public void doAttributeName() {
		builder.doAttributeName(subsequence());
	}

	public void doAttributeValue() {
		builder.doAttributeValue(subsequence());
	}

	public void doCharacters() {
		CharSequence seq = subsequence();
		incrementOffsetAndResetLength();
		if(isBlank(seq)) {
			return;
		} else {
			builder.doCharacters(seq);
		}
	}

	public void doComment() {
		builder.doComment(subsequence());
	}

	public void doElementEnd() {
		incrementOffsetAndResetLength();
		builder = builder.doElementEnd();
	}

	public void doElementStart() {
		builder = builder.doElementStart(subsequence());
	}

	public void doProcessingInstruction() {
		builder.doProcessingInstruction(subsequence());
	}

	public void doProcessingInstructionEnd() {
		// 
	}
	
    public static boolean isBlank(@Nonnull CharSequence str) {
        for (int i = 0, strLen = str.length(); i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
	
	public XMLParser() {
		this.builder = new DocumentBuilder();
	}

	void incrementOffsetAndResetLength() {
		offset = limit;
		offset++;
	}
	
	private CharSequence subsequence() {
		return sequence.subSequence(offset, limit);
	}
	
	public void parse(CharSequence seq) {
		int max = seq.length();
		// Yank state into a stack local, reduces benchmark by 10%
		State currentState = this.state;
		// make seq available to the subsequence method without making offset and limit visible
		this.sequence = seq;
		for(offset = 0, limit = 0 ; limit < max ; ++limit ) {
			currentState = currentState.parse(seq.charAt(limit), this);
		}
		this.state = currentState;
	}

	public void doCData() {
		CharSequence cdata = subsequence();
		builder.doCharacters(cdata.subSequence(0, cdata.length() - 2));
	}
	
}
