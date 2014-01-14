/* JNativeHook: Global keyboard and mouse hooking for Java.
 * Copyright (C) 2006-2014 Alexander Barker.  All Rights Received.
 * http://code.google.com/p/jnativehook/
 *
 * JNativeHook is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JNativeHook is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jnativehook.keyboard;

//Imports

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;

import java.awt.Toolkit;

/**
 * An event which indicates that a keystroke occurred at global scope.
 * <p/>
 *
 * This low-level event is generated by the native system when a key is pressed
 * or released independent of Java component focus. The event is passed to every
 * <code>NativeKeyListener</code> object which has been registered to receive
 * global key events using the
 * {@link GlobalScreen#addNativeKeyListener(NativeKeyListener)} method. Each
 * <code>NativeKeyListener</code> object will receive a
 * <code>NativeKeyEvent</code> when the event occurs.
 * <p/>
 *
 * All <code>NativeKeyEvent</code> objects are dependent on the native platform
 * and keyboard layout. <code>NATIVE_KEY_PRESSED</code> and
 * <code>NATIVE_KEY_RELEASED</code> are generated for every key code received by
 * the native system. The key being pressed or released is indicated by the
 * getKeyCode method, which returns a virtual key code. Native virtual
 * keyCode constants maybe equivalent to their {@link java.awt.event.KeyEvent}
 * counterparts, however this this not guaranteed.
 * <p/>
 *
 * <code>NATIVE_KEY_TYPED</code> events are produced for
 * <code>NATIVE_KEY_PRESSED</code> events that produce valid Unicode character
 * for the native keyboard layout. The getKeyChar method always returns a valid
 * Unicode character or <code>CHAR_UNDEFINED</code>. For
 * <code>NATIVE_KEY_PRESSED</code> and <code>NATIVE_KEY_RELEASED</code> events,
 * the getKeyCode method returns the event's keyCode. For
 * <code>NATIVE_KEY_TYPED</code> events, the getKeyCode method always returns
 * <code>VC_UNDEFINED</code>.
 * <p/>
 *
 * Virtual key codes only represent the physical key has been pressed and should
 * not be mistaken with the character mapped to that key by the operating
 * system.  To determine the Unicode representation of the
 * <code>NativeKeyEvent</code>, please use the {@link #getKeyChar()} method
 * for the <code>NATIVE_KEY_TYPED</code> event associated with that virtual key
 * codes.
 * <p/>
 *
 * @author	Alexander Barker (<a href="mailto:alex@1stleg.com">alex@1stleg.com</a>)
 * @version	1.1
 *
 * @see GlobalScreen
 * @see NativeKeyListener
 */
public class NativeKeyEvent extends NativeInputEvent {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2206695554060864203L;

	/** The raw native key code. */
	private int rawCode;

	/** The virtual key code. */
	private int keyCode;

	/** The Unicode character. */
	private char keyChar;

	/** The virtual key location. */
	private int keyLocation;


	/** The first number in the range of ID's used for native key events. */
	public static final int NATIVE_KEY_FIRST				= 2400;

	/** The last number in the range of ID's used for native key events. */
	public static final int NATIVE_KEY_LAST					= 2402;

	/** The "native key typed" event ID. */
	public static final int NATIVE_KEY_TYPED				= NATIVE_KEY_FIRST;

	/** The "native key pressed" event ID. */
	public static final int NATIVE_KEY_PRESSED				= 1 + NATIVE_KEY_FIRST;

	/** The "native key released" event ID. */
	public static final int NATIVE_KEY_RELEASED				= 2 + NATIVE_KEY_FIRST;

	public static final int KEY_LOCATION_UNKNOWN			= 0;
	public static final int KEY_LOCATION_STANDARD			= 1;
	public static final int KEY_LOCATION_LEFT				= 2;
	public static final int KEY_LOCATION_RIGHT				= 3;
	public static final int KEY_LOCATION_NUMPAD				= 4;


	public static final int VC_ESCAPE						= 0x0001;
			
	/** Constants for the F1 through F24 function keys. */
	public static final int VC_F1							= 0x003B;
	public static final int VC_F2							= 0x003C;
	public static final int VC_F3							= 0x003D;
	public static final int VC_F4							= 0x003E;
	public static final int VC_F5							= 0x003F;
	public static final int VC_F6							= 0x0040;
	public static final int VC_F7							= 0x0041;
	public static final int VC_F8							= 0x0042;
	public static final int VC_F9							= 0x0043;
	public static final int VC_F10							= 0x0044;
	public static final int VC_F11							= 0x0057;
	public static final int VC_F12							= 0x0058;

	public static final int VC_F13							= 0x005B;
	public static final int VC_F14							= 0x005C;
	public static final int VC_F15							= 0x005D;
	public static final int VC_F16							= 0x0063;
	public static final int VC_F17							= 0x0064;
	public static final int VC_F18							= 0x0065;
	public static final int VC_F19							= 0x0066;
	public static final int VC_F20							= 0x0067;
	public static final int VC_F21							= 0x0068;
	public static final int VC_F22							= 0x0069;
	public static final int VC_F23							= 0x006A;
	public static final int VC_F24							= 0x006B;
	
	public static final int VC_BACKQUOTE					= 0x0029;
		
	/** VC_0 thru VC_9 are the same as ASCII '0' thru '9' (0x30 - 0x39). */
	public static final int VC_1							= 0x0002;
	public static final int VC_2							= 0x0003;
	public static final int VC_3							= 0x0004;
	public static final int VC_4							= 0x0005;
	public static final int VC_5							= 0x0006;
	public static final int VC_6							= 0x0007;
	public static final int VC_7							= 0x0008;
	public static final int VC_8							= 0x0009;
	public static final int VC_9							= 0x000A;
	public static final int VC_0							= 0x000B;
	
	public static final int VC_MINUS						= 0x000C;	// '-'
	public static final int VC_EQUALS						= 0x000D;	// '='
	public static final int VC_BACKSPACE					= 0x000E;

	public static final int VC_TAB							= 0x000F;
	public static final int VC_CAPS_LOCK					= 0x003A;
	
	/** VC_A thru VC_Z */
	public static final int VC_A							= 0x001E;
	public static final int VC_B							= 0x0030;
	public static final int VC_C							= 0x002E;
	public static final int VC_D							= 0x0020;
	public static final int VC_E							= 0x0012;
	public static final int VC_F							= 0x0021;
	public static final int VC_G							= 0x0022;
	public static final int VC_H							= 0x0023;
	public static final int VC_I							= 0x0017;
	public static final int VC_J							= 0x0024;
	public static final int VC_K							= 0x0025;
	public static final int VC_L							= 0x0026;
	public static final int VC_M							= 0x0032;
	public static final int VC_N							= 0x0031;
	public static final int VC_O							= 0x0018;
	public static final int VC_P							= 0x0019;
	public static final int VC_Q							= 0x0010;
	public static final int VC_R							= 0x0013;
	public static final int VC_S							= 0x001F;
	public static final int VC_T							= 0x0014;
	public static final int VC_U							= 0x0016;
	public static final int VC_V							= 0x002F;
	public static final int VC_W							= 0x0011;
	public static final int VC_X							= 0x002D;
	public static final int VC_Y							= 0x0015;
	public static final int VC_Z							= 0x002C;
	
	public static final int VC_OPEN_BRACKET					= 0x001A;	// '['
	public static final int VC_CLOSE_BRACKET				= 0x001B;	// ']'
	public static final int VC_BACK_SLASH					= 0x002B;	// '\'

	public static final int VC_SEMICOLON					= 0x0027;	// ';'
	public static final int VC_QUOTE						= 0x0028;
	public static final int VC_ENTER						= 0x001C;

	public static final int VC_COMMA						= 0x0033;	// ','
	public static final int VC_PERIOD						= 0x0034;	// '.'
	public static final int VC_SLASH						= 0x0035;	// '/'
	
	public static final int VC_SPACE						= 0x0039;


	public static final int VC_PRINTSCREEN					= 0x0E37;
	public static final int VC_SCROLL_LOCK					= 0x0046;
	public static final int VC_PAUSE						= 0x0E45;


	// Begin Edit Key Zone
	public static final int VC_INSERT						= 0x0E52;
	public static final int VC_DELETE						= 0x0E53;
	public static final int VC_HOME							= 0x0E47;
	public static final int VC_END							= 0x0E4F;
	public static final int VC_PAGE_UP						= 0x0E49;
	public static final int VC_PAGE_DOWN					= 0x0E51;


	// Begin Cursor Key Zone
	public static final int VC_UP							= 0xE048;
	public static final int VC_LEFT							= 0xE04B;
	public static final int VC_RIGHT						= 0xE04D;
	public static final int VC_DOWN							= 0xE050;


	/** Begin Numeric Zone */
	public static final int VC_NUM_LOCK						= 0x0045;
	public static final int VC_KP_DIVIDE					= 0x0E35;
	public static final int VC_KP_MULTIPLY					= 0x0037;
	public static final int VC_KP_SUBTRACT					= 0x004A;
	public static final int VC_KP_EQUALS					= 0x0E0D;
	public static final int VC_KP_ADD						= 0x004E;
	public static final int VC_KP_ENTER						= 0x0E1C;
	public static final int VC_KP_SEPARATOR					= 0x0053;

	public static final int VC_KP_1							= 0x004F;
	public static final int VC_KP_2							= 0x0050;
	public static final int VC_KP_3							= 0x0051;
	public static final int VC_KP_4							= 0x004B;
	public static final int VC_KP_5							= 0x004C;
	public static final int VC_KP_6							= 0x004D;
	public static final int VC_KP_7							= 0x0047;
	public static final int VC_KP_8							= 0x0048;
	public static final int VC_KP_9							= 0x0049;
	public static final int VC_KP_0							= 0x0052;


	/**  Modifier and Control Keys */
	public static final int VC_SHIFT_L						= 0x002A;
	public static final int VC_SHIFT_R						= 0x0036;
	public static final int VC_CONTROL_L					= 0x001D;
	public static final int VC_CONTROL_R					= 0x0E1D;
	public static final int VC_ALT_L						= 0x0038;	// Option or Alt Key
	public static final int VC_ALT_R						= 0x0E38;	// Option or Alt Key
	public static final int VC_META_L						= 0x0E5B;	// Windows or Command Key
	public static final int VC_META_R						= 0x0E5C;	// Windows or Command Key
	public static final int VC_CONTEXT_MENU					= 0x0E5D;


	/**  Media and Extra Keys */
	public static final int VC_POWER						= 0xE05E;
	public static final int VC_SLEEP						= 0xE05F;
	public static final int VC_WAKE							= 0xE063;
	public static final int VC_MUTE							= 0xE020;
	public static final int VC_VOLUME_UP					= 0xE030;
	public static final int VC_VOLUME_DOWN					= 0xE02E;
	public static final int VC_CUT							= 0xE017;
	public static final int VC_COPY							= 0xE018;
	public static final int VC_PASTE						= 0xE00A;
	public static final int VC_HELP							= 0xE03B;
	public static final int VC_UNDO							= 0xE008;
	public static final int VC_REDO							= 0xE007;
	public static final int VC_PLAY							= 0xE022;
	public static final int VC_STOP							= 0xE024;
	public static final int VC_REWIND						= 0xE010;
	public static final int VC_FAST_FORWARD					= 0xE019;
	public static final int VC_EJECT						= 0xE02C;
	public static final int VC_MAIL							= 0xE01E;
	public static final int VC_WEB							= 0xE032;
	public static final int VC_MUSIC						= 0xE03C;
	public static final int VC_PICTURES						= 0xE064;
	public static final int VC_VIDEO						= 0xE06D;


	public static final int VC_KATAKANA						= 0x0070;
	public static final int VC_FURIGANA						= 0x0077;
	public static final int VC_KANJI						= 0x0079;
	public static final int VC_HIRAGANA						= 0x007B;

	/** This value is used to indicate that the keyCode is unknown. */
	public static final int VC_UNDEFINED					= 0x0000;
	
	/** This is used to indicate that the keyChar is undefined. */
	public static final char CHAR_UNDEFINED					= 0xFFFF;

	/**
	 * Instantiates a new native key event.
	 * <p>
	 * Note that passing in an invalid ID results in unspecified behavior.
	 * @param id an integer that identifies the native event type.
	 * @param when the timestamp for the native event.
	 * @param modifiers the modifier mask for the native event.
	 * <code>NativeInputEvent</code> _MASK modifiers should be used as they are
	 * not compatible with AWT's <code>InputEvent</code> _DOWN_MASK or the older
	 * _MASK modifiers.
	 * @param rawCode the hardware code associated with the native key in this
	 * event.
	 * @param keyCode the virtual key code generated by this event, or
	 * VC_UNDEFINED (for a key-typed event)
	 * @param keyChar the Unicode character generated by this event, or
	 * CHAR_UNDEFINED (for key-pressed and key-released events which do not map
	 * to a valid Unicode character).
	 * @param keyLocation the location ID of the key generating this event.
	 *
	 * @since 1.1
	 */
	public NativeKeyEvent(int id, long when, int modifiers, int rawCode, int keyCode, char keyChar, int keyLocation) {
		super(GlobalScreen.getInstance(), id, when, modifiers);

		this.rawCode = rawCode;
		this.keyCode = keyCode;
		this.keyChar = keyChar;
		this.keyLocation = keyLocation;

		if (id == NATIVE_KEY_TYPED && (keyChar == CHAR_UNDEFINED || keyCode != VC_UNDEFINED)) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Instantiates a new native key event.
	 * <p>
	 * Note that passing in an invalid ID results in unspecified behavior.
	 * @param id an integer that identifies the native event type.
	 * @param when the timestamp for the native event.
	 * @param modifiers the modifier mask for the native event.
	 * <code>NativeInputEvent</code> _MASK modifiers should be used as they are
	 * not compatible with AWT's <code>InputEvent</code> _DOWN_MASK or the older
	 * _MASK modifiers.
	 * @param rawCode the hardware code associated with the native key in this
	 * event.
	 * @param keyCode the virtual key code generated by this event, or
	 * VC_UNDEFINED (for a key-typed event)
	 * @param keyChar the Unicode character generated by this event, or
	 * CHAR_UNDEFINED (for key-pressed and key-released events which do not map
	 * to a valid Unicode character).
	 *
	 * @since 1.1
	 */
	public NativeKeyEvent(int id, long when, int modifiers, int rawCode, int keyCode, char keyChar) {
		this(id, when, modifiers, rawCode, keyCode, keyChar, KEY_LOCATION_UNKNOWN);
	}

	/**
	 * Returns the native code associated with the native key in this event.
	 * This is an arbitrary number between 0 and 255 (inclusive) used by the
	 * operating system to represent a physical key on the keyboard.  This
	 * code does not necessarily represent the native key's scan code or ASCII
	 * representation.  To maintain platform independence, you should not rely
	 * on the consistency of this value from platform to platform.
	 *
	 * @return the native key code for this event.
	 */
	public int getRawCode() {
		return this.rawCode;
	}

	/**
	 * Set the rawCode value in this event. For more information on what this
	 * value represents please see {@link #getRawCode()}.
	 *
	 *
	 *
	 * @param rawCode the native key code for this event.
	 */
	public void setRawCode(int rawCode) {
		 this.rawCode = rawCode;
	}

	/**
	 * Returns the keyCode associated with this event.
	 *
	 * @return the native virtual key code.
	 */
	public int getKeyCode() {
		return this.keyCode;
	}

	/**
	 * Set the keyCode value in this event.
	 *
	 * @param keyCode the native virtual key code.
	 */
	public void setKeyCode(int keyCode) {
		 this.keyCode = keyCode;
	}

	/**
	 * Returns the native Unicode character associated with this event.
	 *
	 * @return the Unicode character defined for this key event. If no valid
	 * Unicode character exists for this key event, <code>CHAR_UNDEFINED</code>
	 * is returned.
	 */
	public char getKeyChar() {
		return this.keyChar;
	}

	/**
	 * Set the keyChar value in this event.  For example, the
	 * <code>NATIVE_KEY_TYPED</code> event for Shift + "a" returns the Unicode
	 * value 'A'.
	 *
	 * @param keyChar the keyboard character associated with this event.
	 */
	public void setKeyChar(char keyChar) {
		 this.keyChar = keyChar;
	}

	/**
	 * Returns the location of the virtual key for this event.
	 *
	 * @return the location of the virtual key that was pressed or released.
	 */
	public int getKeyLocation() {
		return this.keyLocation;
	}

	/**
	 * Returns a String describing the keyCode, such as "HOME", "F1" or "A".
	 * These strings can be localized by changing the awt.properties file.
	 *
	 * @param keyCode the native virtual key code generated by this event
	 * @return a string containing a text description for a physical key,
	 * identified by its keyCode.
	 */
	public static String getKeyText(int keyCode) {
		// Lookup text values.
		switch (keyCode) {
			case VC_A:
				return "A";
			case VC_B:
				return "B";
			case VC_C:
				return "C";
			case VC_D:
				return "D";
			case VC_E:
				return "E";
			case VC_F:
				return "F";
			case VC_G:
				return "G";
			case VC_H:
				return "H";
			case VC_I:
				return "I";
			case VC_J:
				return "J";
			case VC_K:
				return "K";
			case VC_L:
				return "L";
			case VC_M:
				return "M";
			case VC_N:
				return "N";
			case VC_O:
				return "O";
			case VC_P:
				return "P";
			case VC_Q:
				return "Q";
			case VC_R:
				return "R";
			case VC_S:
				return "S";
			case VC_T:
				return "T";
			case VC_U:
				return "U";
			case VC_V:
				return "V";
			case VC_W:
				return "W";
			case VC_X:
				return "X";
			case VC_Y:
				return "Y";
			case VC_Z:
				return "Z";

			case VC_KP_0:
				return Toolkit.getProperty("AWT.numpad0", "NumPad 0");
			case VC_KP_1:
				return Toolkit.getProperty("AWT.numpad1", "NumPad 1");
			case VC_KP_2:
				return Toolkit.getProperty("AWT.numpad1", "NumPad 2");
			case VC_KP_3:
				return Toolkit.getProperty("AWT.numpad1", "NumPad 3");
			case VC_KP_4:
				return Toolkit.getProperty("AWT.numpad1", "NumPad 4");
			case VC_KP_5:
				return Toolkit.getProperty("AWT.numpad1", "NumPad 5");
			case VC_KP_6:
				return Toolkit.getProperty("AWT.numpad1", "NumPad 6");
			case VC_KP_7:
				return Toolkit.getProperty("AWT.numpad1", "NumPad 7");
			case VC_KP_8:
				return Toolkit.getProperty("AWT.numpad1", "NumPad 8");
			case VC_KP_9:
				return Toolkit.getProperty("AWT.numpad1", "NumPad 9");


			case VC_0:
				return "0";
			case VC_1:
				return "1";
			case VC_2:
				return "2";
			case VC_3:
				return "3";
			case VC_4:
				return "4";
			case VC_5:
				return "5";
			case VC_6:
				return "6";
			case VC_7:
				return "7";
			case VC_8:
				return "8";
			case VC_9:
				return "9";


			case VC_KP_ENTER:
			case VC_ENTER:
				return Toolkit.getProperty("AWT.enter", "Enter");
			case VC_BACKSPACE:
				return Toolkit.getProperty("AWT.backSpace", "Backspace");
			case VC_TAB:
				return Toolkit.getProperty("AWT.tab", "Tab");

			case VC_SHIFT_L:
			case VC_SHIFT_R:
				return	Toolkit.getProperty("AWT.shift", "Shift");
			case VC_CONTROL_L:
			case VC_CONTROL_R:
				return Toolkit.getProperty("AWT.control", "Control");
			case VC_ALT_L:
			case VC_ALT_R:
				return Toolkit.getProperty("AWT.alt", "Alt");
			case VC_META_L:
			case VC_META_R:
				return Toolkit.getProperty("AWT.meta", "Meta");
			case VC_CONTEXT_MENU:
				return Toolkit.getProperty("AWT.context", "Context Menu");

			case VC_PAUSE:
				return Toolkit.getProperty("AWT.pause", "Pause");
			case VC_CAPS_LOCK:
				return Toolkit.getProperty("AWT.capsLock", "Caps Lock");
			case VC_ESCAPE:
				return Toolkit.getProperty("AWT.escape", "Escape");
			case VC_SPACE:
				return Toolkit.getProperty("AWT.space", "Space");

			case VC_UP:
				return Toolkit.getProperty("AWT.up", "Up");
			case VC_DOWN:
				return Toolkit.getProperty("AWT.down", "Down");
			case VC_LEFT:
				return Toolkit.getProperty("AWT.left", "Left");
			case VC_RIGHT:
				return Toolkit.getProperty("AWT.right", "Right");


			case VC_COMMA:
				return Toolkit.getProperty("AWT.comma", "Comma");
			case VC_MINUS:
				return Toolkit.getProperty("AWT.minus", "Minus");
			case VC_PERIOD:
				return Toolkit.getProperty("AWT.period", "Period");
			case VC_SLASH:
				return Toolkit.getProperty("AWT.slash", "Slash");

			case VC_KP_EQUALS:
				// TODO Not sure if apple defines an AWT property for this key.
			case VC_EQUALS:
				return Toolkit.getProperty("AWT.equals", "Equals");
			case VC_SEMICOLON:
				return Toolkit.getProperty("AWT.semicolon", "Semicolon");


			case VC_OPEN_BRACKET:
				return Toolkit.getProperty("AWT.openBracket", "Open Bracket");
			case VC_BACK_SLASH:
				return Toolkit.getProperty("AWT.backSlash", "Back Slash");
			case VC_CLOSE_BRACKET:
				return Toolkit.getProperty("AWT.closeBracket", "Close Bracket");


			case VC_KP_MULTIPLY:
				return Toolkit.getProperty("AWT.multiply", "NumPad Multiply");
			case VC_KP_ADD:
				return Toolkit.getProperty("AWT.add", "NumPad Add");
			case VC_KP_SUBTRACT:
				return Toolkit.getProperty("AWT.subtract", "NumPad Subtract");
			case VC_KP_SEPARATOR:
				return Toolkit.getProperty("AWT.separator", "NumPad Separator");
			case VC_KP_DIVIDE:
				return Toolkit.getProperty("AWT.divide", "NumPad Divide");
			case VC_DELETE:
				return Toolkit.getProperty("AWT.delete", "NumPad Delete");
			case VC_NUM_LOCK:
				return Toolkit.getProperty("AWT.numLock", "Num Lock");
			case VC_SCROLL_LOCK:
				return Toolkit.getProperty("AWT.scrollLock", "Scroll Lock");


			/* F1 through F24 function keys. */
			case VC_F1:
				return Toolkit.getProperty("AWT.f1", "F1");
			case VC_F2:
				return Toolkit.getProperty("AWT.f2", "F2");
			case VC_F3:
				return Toolkit.getProperty("AWT.f3", "F3");
			case VC_F4:
				return Toolkit.getProperty("AWT.f4", "F4");
			case VC_F5:
				return Toolkit.getProperty("AWT.f5", "F5");
			case VC_F6:
				return Toolkit.getProperty("AWT.f6", "F6");
			case VC_F7:
				return Toolkit.getProperty("AWT.f7", "F7");
			case VC_F8:
				return Toolkit.getProperty("AWT.f8", "F8");
			case VC_F9:
				return Toolkit.getProperty("AWT.f9", "F9");
			case VC_F10:
				return Toolkit.getProperty("AWT.f10", "F10");
			case VC_F11:
				return Toolkit.getProperty("AWT.f11", "F11");
			case VC_F12:
				return Toolkit.getProperty("AWT.f12", "F12");

			case VC_F13:
				return Toolkit.getProperty("AWT.f13", "F13");
			case VC_F14:
				return Toolkit.getProperty("AWT.f14", "F14");
			case VC_F15:
				return Toolkit.getProperty("AWT.f15", "F15");
			case VC_F16:
				return Toolkit.getProperty("AWT.f16", "F16");
			case VC_F17:
				return Toolkit.getProperty("AWT.f17", "F17");
			case VC_F18:
				return Toolkit.getProperty("AWT.f18", "F18");
			case VC_F19:
				return Toolkit.getProperty("AWT.f19", "F19");
			case VC_F20:
				return Toolkit.getProperty("AWT.f20", "F20");
			case VC_F21:
				return Toolkit.getProperty("AWT.f21", "F21");
			case VC_F22:
				return Toolkit.getProperty("AWT.f22", "F22");
			case VC_F23:
				return Toolkit.getProperty("AWT.f23", "F23");
			case VC_F24:
				return Toolkit.getProperty("AWT.f24", "F24");


			case VC_PRINTSCREEN:
				return Toolkit.getProperty("AWT.printScreen", "Print Screen");
			case VC_INSERT:
				return Toolkit.getProperty("AWT.insert", "Insert");
			case VC_HELP:
				return Toolkit.getProperty("AWT.help", "Help");


			case VC_PAGE_UP:
				return Toolkit.getProperty("AWT.pgup", "Page Up");
			case VC_PAGE_DOWN:
				return Toolkit.getProperty("AWT.pgdn", "Page Down");
			case VC_HOME:
				return Toolkit.getProperty("AWT.home", "Home");
			case VC_END:
				return Toolkit.getProperty("AWT.end", "End");


			case VC_QUOTE:
				return Toolkit.getProperty("AWT.quote", "Quote");
			case VC_BACKQUOTE:
				return Toolkit.getProperty("AWT.backQuote", "Back Quote");



			/* For European keyboards */
/*
			case VC_DEAD_GRAVE:
				return Toolkit.getProperty("AWT.deadGrave", "Dead Grave");
			case VC_DEAD_ACUTE:
				return Toolkit.getProperty("AWT.deadAcute", "Dead Acute");
			case VC_DEAD_CIRCUMFLEX:
				return Toolkit.getProperty("AWT.deadCircumflex", "Dead Circumflex");
			case VC_DEAD_TILDE:
				return Toolkit.getProperty("AWT.deadTilde", "Dead Tilde");
			case VC_DEAD_MACRON:
				return Toolkit.getProperty("AWT.deadMacron", "Dead Macron");
			case VC_DEAD_BREVE:
				return Toolkit.getProperty("AWT.deadBreve", "Dead Breve");
			case VC_DEAD_ABOVEDOT:
				return Toolkit.getProperty("AWT.deadAboveDot", "Dead Above Dot");
			case VC_DEAD_DIAERESIS:
				return Toolkit.getProperty("AWT.deadDiaeresis", "Dead Diaeresis");
			case VC_DEAD_ABOVERING:
				return Toolkit.getProperty("AWT.deadAboveRing", "Dead Above Ring");
			case VC_DEAD_DOUBLEACUTE:
				return Toolkit.getProperty("AWT.deadDoubleAcute", "Dead Double Acute");
			case VC_DEAD_CARON:
				return Toolkit.getProperty("AWT.deadCaron", "Dead Caron");
			case VC_DEAD_CEDILLA:
				return Toolkit.getProperty("AWT.deadCedilla", "Dead Cedilla");
			case VC_DEAD_OGONEK:
				return Toolkit.getProperty("AWT.deadOgonek", "Dead Ogonek");
			case VC_DEAD_IOTA:
				return Toolkit.getProperty("AWT.deadIota", "Dead Iota");
			case VC_DEAD_VOICED_SOUND:
				return Toolkit.getProperty("AWT.deadVoicedSound", "Dead Voiced Sound");
			case VC_DEAD_SEMIVOICED_SOUND:
				return Toolkit.getProperty("AWT.deadSemivoicedSound", "Dead Semivoiced Sound");
*/
			/* Unknown Keyboard Codes */
/*
			case VC_AMPERSAND:
				return Toolkit.getProperty("AWT.ampersand", "Ampersand");
			case VC_ASTERISK:
				return Toolkit.getProperty("AWT.asterisk", "Asterisk");
			case VC_QUOTEDBL:
				return Toolkit.getProperty("AWT.quoteDbl", "Double Quote");
			case VC_LESS:
				return Toolkit.getProperty("AWT.less", "Less");
			case VC_GREATER:
				return Toolkit.getProperty("AWT.greater", "Greater");
			case VC_BRACELEFT:
				return Toolkit.getProperty("AWT.braceLeft", "Left Brace");
			case VC_BRACERIGHT:
				return Toolkit.getProperty("AWT.braceRight", "Right Brace");
*/

			/* Unknown Extended Keyboard Codes */
/*
			case VC_AT:
				return Toolkit.getProperty("AWT.at", "At");
			case VC_COLON:
				return Toolkit.getProperty("AWT.colon", "Colon");
			case VC_CIRCUMFLEX:
				return Toolkit.getProperty("AWT.circumflex", "Circumflex");
			case VC_DOLLAR:
				return Toolkit.getProperty("AWT.dollar", "Dollar");
			case VC_EURO_SIGN:
				return Toolkit.getProperty("AWT.euro", "Euro");
			case VC_EXCLAMATION_MARK:
				return Toolkit.getProperty("AWT.exclamationMark", "Exclamation Mark");
			case VC_INVERTED_EXCLAMATION_MARK:
				return Toolkit.getProperty("AWT.invertedExclamationMark", "Inverted Exclamation Mark");
			case VC_LEFT_PARENTHESIS:
				return Toolkit.getProperty("AWT.leftParenthesis", "Left Parenthesis");
			case VC_NUMBER_SIGN:
				return Toolkit.getProperty("AWT.numberSign", "Number Sign");
			case VC_PLUS:
				return Toolkit.getProperty("AWT.plus", "Plus");
			case VC_RIGHT_PARENTHESIS:
				return Toolkit.getProperty("AWT.rightParenthesis", "Right Parenthesis");
			case VC_UNDERSCORE:
				return Toolkit.getProperty("AWT.underscore", "Underscore");
*/

			/* For input method support on Asian Keyboards */
/*
			case VC_FINAL:
				return Toolkit.getProperty("AWT.quote", "Quote");
			case VC_CONVERT:
				return Toolkit.getProperty("AWT.convert", "Convert");
			case VC_NONCONVERT:
				return Toolkit.getProperty("AWT.noconvert", "No Convert");
			case VC_ACCEPT:
				return Toolkit.getProperty("AWT.accept", "Accept");
			case VC_MODECHANGE:
				return Toolkit.getProperty("AWT.modechange", "Mode Change");
			case VC_KANA:
				return Toolkit.getProperty("AWT.kana", "Kana");
			case VC_KANJI:
				return Toolkit.getProperty("AWT.kanji", "Kanji");
			case VC_ALPHANUMERIC:
				return Toolkit.getProperty("AWT.alphanumeric", "Alphanumeric");
			case VC_KATAKANA:
				return Toolkit.getProperty("AWT.katakana", "Katakana");
			case VC_HIRAGANA:
				return Toolkit.getProperty("AWT.hiragana", "Hiragana");
			case VC_FULL_WIDTH:
				return Toolkit.getProperty("AWT.fullWidth", "Full-Width");
			case VC_HALF_WIDTH:
				return Toolkit.getProperty("AWT.halfWidth", "Half-Width");
			case VC_ROMAN_CHARACTERS:
				return Toolkit.getProperty("AWT.romanCharacters", "Roman Characters");
			case VC_ALL_CANDIDATES:
				return Toolkit.getProperty("AWT.allCandidates", "All Candidates");
			case VC_PREVIOUS_CANDIDATE:
				return Toolkit.getProperty("AWT.previousCandidate", "Previous Candidate");
			case VC_CODE_INPUT:
				return Toolkit.getProperty("AWT.codeInput", "Code Input");
			case VC_JAPANESE_KATAKANA:
				return Toolkit.getProperty("AWT.japaneseKatakana", "Japanese Katakana");
			case VC_JAPANESE_HIRAGANA:
				return Toolkit.getProperty("AWT.japaneseHiragana", "Japanese Hiragana");
			case VC_JAPANESE_ROMAN:
				return Toolkit.getProperty("AWT.japaneseRoman", "Japanese Roman");
			case VC_KANA_LOCK:
				return Toolkit.getProperty("AWT.kanaLock", "Kana Lock");
			case VC_INPUT_METHOD_ON_OFF:
				return Toolkit.getProperty("AWT.inputMethodOnOff", "Input Method On/Off");
*/

			/* Sun Keyboard keys */
/*
			case VC_AGAIN:
				return Toolkit.getProperty("AWT.again", "Again");
			case VC_UNDO:
				return Toolkit.getProperty("AWT.undo", "Undo");
			case VC_COPY:
				return Toolkit.getProperty("AWT.copy", "Copy");
			case VC_PASTE:
				return Toolkit.getProperty("AWT.paste", "Paste");
			case VC_CUT:
				return Toolkit.getProperty("AWT.cut", "Cut");
			case VC_FIND:
				return Toolkit.getProperty("AWT.find", "Find");
			case VC_PROPS:
				return Toolkit.getProperty("AWT.props", "Props");
			case VC_STOP:
				return Toolkit.getProperty("AWT.stop", "Stop");


			case VC_BEGIN:
				return Toolkit.getProperty("AWT.begin", "Begin");
*/
			case VC_UNDEFINED:
				return Toolkit.getProperty("AWT.undefined", "Undefined");
		}

		return Toolkit.getProperty("AWT.unknown", "Unknown") +
				" keyCode: 0x" + Integer.toString(keyCode, 16);
	}


    /**
     * Returns whether the key in this event is an "action" key.  Typically an
	 * action key does not fire a unicode character and is not a modifier key.
     *
     * @return <code>true</code> if the key is an "action" key,
	 * <code>false</code> otherwise
	 *
	 * @since 1.1
     */
    public boolean isActionKey() {
        switch (keyCode) {
			case VC_SHIFT_L:
			case VC_SHIFT_R:
			case VC_CONTROL_L:
			case VC_CONTROL_R:
			case VC_ALT_L:
			case VC_ALT_R:
			case VC_META_L:
			case VC_META_R:
			case VC_CONTEXT_MENU:

			case VC_UP:
			case VC_DOWN:
			case VC_LEFT:
			case VC_RIGHT:

			case VC_F1:
			case VC_F2:
			case VC_F3:
			case VC_F4:
			case VC_F5:
			case VC_F6:
			case VC_F7:
			case VC_F8:
			case VC_F9:
			case VC_F10:
			case VC_F11:
			case VC_F12:

			case VC_F13:
			case VC_F14:
			case VC_F15:
			case VC_F16:
			case VC_F17:
			case VC_F18:
			case VC_F19:
			case VC_F20:
			case VC_F21:
			case VC_F22:
			case VC_F23:
			case VC_F24:

			case VC_PRINTSCREEN:
			case VC_INSERT:
			case VC_HELP:

			case VC_PAGE_UP:
			case VC_PAGE_DOWN:
			case VC_HOME:
			case VC_END:




			case VC_SCROLL_LOCK:
			case VC_CAPS_LOCK:
			case VC_NUM_LOCK:


			/* Sun Keyboard keys */
			case VC_UNDO:
			case VC_COPY:
			case VC_PASTE:
			case VC_CUT:
			case VC_STOP:

			/* For input method support on Asian Keyboards */
			case VC_KANJI:
			case VC_KATAKANA:
			case VC_HIRAGANA:
				return true;
        }

        return false;
    }


	/**
	 * Returns a parameter string identifying this event. This method is useful
	 * for event logging and debugging.
	 *
	 * @return a string identifying the event and its attributes.
	 */
	@Override
	public String paramString() {
		StringBuilder param = new StringBuilder(255);

		switch(getID()) {
			case NATIVE_KEY_PRESSED:
				param.append("NATIVE_KEY_PRESSED");
				break;

			case NATIVE_KEY_RELEASED:
				param.append("NATIVE_KEY_RELEASED");
				break;

			case NATIVE_KEY_TYPED:
				param.append("NATIVE_KEY_TYPED");
				break;

			default:
				param.append("unknown type");
				break;
		}
		param.append(',');

		param.append("keyCode=");
		param.append(keyCode);
		param.append(',');

		param.append("keyText=");
		param.append(getKeyText(keyCode));
		param.append(',');

		param.append("keyChar=");
		switch (keyChar) {
			case VC_BACKSPACE:
			case VC_DELETE:
			case VC_ESCAPE:
			case VC_ENTER:
			case VC_TAB:
				param.append(getKeyText(keyChar));
				break;
			case CHAR_UNDEFINED:
				param.append(getKeyText(VC_UNDEFINED));
				break;
			default:
				param.append('\'');
				param.append(keyChar);
				param.append('\'');
				break;

		}
		param.append(',');

		if (getModifiers() != 0) {
			param.append("modifiers=");
			param.append(getModifiersText(getModifiers()));
			param.append(',');
		}

		param.append("keyLocation=");
		switch (keyLocation) {
			case KEY_LOCATION_UNKNOWN:
				param.append("KEY_LOCATION_UNKNOWN");
				break;

			case KEY_LOCATION_STANDARD:
				param.append("KEY_LOCATION_STANDARD");
				break;

			case KEY_LOCATION_LEFT:
				param.append("KEY_LOCATION_LEFT");
				break;

			case KEY_LOCATION_RIGHT:
				param.append("KEY_LOCATION_RIGHT");
				break;

			case KEY_LOCATION_NUMPAD:
				param.append("KEY_LOCATION_NUMPAD");
				break;

			default:
				param.append("KEY_LOCATION_UNKNOWN");
				break;
			}
		param.append(',');

		param.append("rawCode=");
		param.append(rawCode);

		return param.toString();
	}
}
