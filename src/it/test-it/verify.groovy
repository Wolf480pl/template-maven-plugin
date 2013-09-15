String path = "target/generated-test-sources/template/com/github/wolf480pl/it/"

File tint = new File(basedir, path +  "IntFooTest.java");
File tfloat = new File(basedir, path + "FloatFooTest.java");
File tlong = new File(basedir, path +  "LongFooTest.java");
File tdouble = new File(basedir, path +  "DoubleFooTest.java");

assert tint.isFile();
assert tfloat.isFile();
assert tlong.isFile();
assert tdouble.isFile();
