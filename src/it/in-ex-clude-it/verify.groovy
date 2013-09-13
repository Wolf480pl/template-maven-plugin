String path = "target/generated-sources/template/com/github/wolf480pl/it/"
File foo = new File( basedir, path + "Foo.java" );

File byteBar = new File (basedir, path + "ByteBar.java");
File charBar = new File (basedir, path + "CharBar.java");
File doubleBar = new File (basedir, path + "DoubleBar.java");
File floatBar = new File (basedir, path + "FloatBar.java");
File intBar = new File (basedir, path + "IntBar.java");
File longBar = new File (basedir, path + "LongBar.java");
File shortBar = new File (basedir, path + "ShortBar.java");

File byteTest = new File (basedir, path + "ByteTest.java");
File charTest = new File (basedir, path + "CharTest.java");
File doubleTest = new File (basedir, path + "DoubleTest.java");
File floatTest = new File (basedir, path + "FloatTest.java");
File intTest = new File (basedir, path + "IntTest.java");
File longTest = new File (basedir, path + "LongTest.java");
File shortTest = new File (basedir, path + "ShortTest.java");

File byteTst = new File (basedir, path + "ByteTst.java");
File charTst = new File (basedir, path + "CharTst.java");
File doubleTst = new File (basedir, path + "DoubleTst.java");
File floatTst = new File (basedir, path + "FloatTst.java");
File intTst = new File (basedir, path + "IntTst.java");
File longTst = new File (basedir, path + "LongTst.java");
File shortTst = new File (basedir, path + "ShortTst.java");

File barByte = new File (basedir, path + "BarByte.java");
File barChar = new File (basedir, path + "BarChar.java");
File barDouble = new File (basedir, path + "BarDouble.java");
File barFloat = new File (basedir, path + "BarFloat.java");
File barInt = new File (basedir, path + "BarInt.java");
File barLong = new File (basedir, path + "BarLong.java");
File barShort = new File (basedir, path + "BarShort.java");

assert foo.isFile();

assert byteBar.isFile();
assert charBar.isFile();
assert doubleBar.isFile();
assert floatBar.isFile();
assert intBar.isFile();
assert longBar.isFile();
assert shortBar.isFile();

assert byteTest.isFile();
assert charTest.isFile();
assert doubleTest.isFile();
assert floatTest.isFile();
assert intTest.isFile();
assert longTest.isFile();
assert shortTest.isFile();

assert !byteTst.isFile();
assert !charTst.isFile();
assert !doubleTst.isFile();
assert !floatTst.isFile();
assert !intTst.isFile();
assert !longTst.isFile();
assert !shortTst.isFile();

assert !barByte.isFile();
assert !barChar.isFile();
assert !barDouble.isFile();
assert !barFloat.isFile();
assert !barInt.isFile();
assert !barLong.isFile();
assert !barShort.isFile();
