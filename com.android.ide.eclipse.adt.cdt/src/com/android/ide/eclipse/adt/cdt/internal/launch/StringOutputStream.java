package com.android.ide.eclipse.adt.cdt.internal.launch;

import java.io.IOException;
import java.io.OutputStream;

public class StringOutputStream extends OutputStream {

	private StringBuffer buffer = new StringBuffer();
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	
	@Override
	public void write(int b) throws IOException {
		buffer.append((char)b);
	}
	
}
