/*
    Copyright 2012, Strategic Gains, Inc.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package com.strategicgains.repoexpress.adapter;

import java.nio.charset.Charset;

import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.repoexpress.exception.InvalidObjectIdException;


/**
 * Converts a String ID to a byte array (e.g. byte[]) using the UTF-8 character set.
 * 
 * @author toddf
 * @since Oct 25, 2012
 * @deprecated
 */
public class StringToByteArrayIdAdapter
implements IdentiferAdapter<byte[]>
{
	private static final Charset UTF8 = Charset.forName("UTF-8");

	/**
	 * throws InvalidObjectIdException if the ID is null.
	 */
	@Override
	public byte[] convert(Identifier id)
	{
		if (id == null || id.isEmpty()) throw new InvalidObjectIdException("null ID");
		if (id.size() > 1) throw new InvalidObjectIdException("ID has too many components: " + id.toString());

        return id.components().get(0).toString().getBytes(UTF8);
	}
}
