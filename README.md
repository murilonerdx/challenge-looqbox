### Would you like to work with us? Apply [here](https://app.pipefy.com/public_form/840222)!

# Looqbox Backend Challenge
![Looqbox](https://github.com/looqbox/looqbox-backend-challenge/blob/master/logo.png)

## Challenge
In this challenge you will need to build a **Microservice** using the stack below and a provided api.

We will not use anything from your project other than evaluate your skills and you are free to use this project in your portfolio.

## Stack
We use:
- Java/Kotlin
- `Spring Boot` for the framework
- `Gradle` for dependency management and local deployment

## Submitting
- Make a fork of this repository
- When you're done send us a pull request

# Guidelines
You need to make a HTTP REST API that 
- Consumes the [PokeAPI](https://pokeapi.co/) data.
- Provides an endpoint to query pokemons based on the substring of its name. For example:
  - Request: `GET /pokemons?q=pidge`
  - Expected response: ```{"result" : ["pidgey", "pidgeotto", "pidgeot"]}```
- You need to apply sorting by two algorithms (it is not permitted to use a sorting library, for this particular feature you must implement by yourself). And it’s very important to explain your implemented logic (For instance, you can use inline comments on the source code): 
  - the pokemon name's length and; 
  - the pokemon name's alphabetical order 
 
- Find a way to indicate the pokemon name highlight regarding the piece of its queried name. For example:
  - The queried name was `pi`
  - The highlight object must be ```{"name": "pikachu", "highlight": "<pre>pi</pre>"}``` or ```{"name": "pikachu", "start": 0, "end": 2}```
- Draw a diagram explaining your architecture

## Bonus points!
- Design Patterns
- Unit Testing
- Dockerize the application
- Explain the Big-Ω (time complexity) of your sorting algorithms (explain how you calculated them)

## Useful links
- [Spring Framework](https://spring.io/)
- [Gradle](https://gradle.org/)
- [PokeApi docs](https://pokeapi.co/docs/v2.html)


## Algorithm

### Quicksort

```java
  public static void quickSort(int[] arr, int left, int right) {
    var pivotIndex = left + (right - left) / 2;
    var pivotValue = arr[pivotIndex];
    var i = left;
    var j = right;
    while (i <= j) {
      while (arr[i] < pivotValue) {
        i++;
      }
      while (arr[j] > pivotValue) {
        j--;
      }
      if (i <= j) {
        var tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        i++;
        j--;
      }
      if (left < i) {
        quickSort(arr, left, j);
      }
      if (right > i) {
        quickSort(arr, i, right);
      }
    }
  }
```

### Bubblesort

```java
  public static void bubbleSort(int[] arr) {
    var lastIndex = arr.length - 1;

    for(var j = 0; j < lastIndex; j++) {
      for(var i = 0; i < lastIndex - j; i++) {
        if(arr[i] > arr[i + 1]) {
          var tmp = arr[i];
          arr[i] = arr[i + 1];
          arr[i + 1] = tmp;
        }
      }
    }
  }
```

### Selectionsort

```java
  public static void selectionSort(int[] arr) {
    var len = arr.length;
        
    for (var i = 0; i < len - 1; i++) {
      var minIndex = i;
        
      for (var j = i + 1; j < len; j++) {
        if(arr[j] < arr[minIndex])
          minIndex = j;
      }
        
      var tmp = arr[minIndex];
      arr[minIndex] = arr[i];
      arr[i] = tmp;
    }
  }
```

### InsertionSort

```java
  public static void insertionSort(int[] arr) {
    for (var i = 1; i < arr.length; i++) {
      var tmp = arr[i];
      var j = i - 1;

      while (j >= 0 && arr[j] > tmp) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = tmp;
    }
  }
```

## Array

### Generic two array concatenation

```java
  public static <T> T[] arrayConcat(T[] first, T[] second) {
    var result = Arrays.copyOf(first, first.length + second.length);
    System.arraycopy(second, 0, result, first.length, second.length);
    return result;
  }
```

### Generic N array concatenation

```java
  public static <T> T[] nArrayConcat(T[] first, T[]... rest) {
    var totalLength = first.length;
    for (var array : rest) {
      totalLength += array.length;
    }
    var result = Arrays.copyOf(first, totalLength);
    var offset = first.length;
    for (var array : rest) {
      System.arraycopy(array, 0, result, offset, array.length);
      offset += array.length;
    }
    return result;
  }
```

### Check if all elements of array are equal

```java
  public static <T> boolean allEqual(T[] arr) {
    return Arrays.stream(arr).distinct().count() == 1;
  }
```

### Find maximum integer from the array

```java
  public static int findMax(int[] arr) {
    return Arrays.stream(arr).reduce(Integer.MIN_VALUE, Integer::max);
  }
```

## Encoding

### Base64 encode string

```java
  public static String encodeBase64(String input) {
    return Base64.getEncoder().encodeToString(input.getBytes());
  }
```

### Base64 decode string

```java
  public static String decodeBase64(String input) {
    return new String(Base64.getDecoder().decode(input.getBytes()));
  }
```

## File

### List directories

```java
  public static File[] listDirectories(String path) {
    return new File(path).listFiles(File::isDirectory);
  }
```

### List files in directory

```java
  public static File[] listFilesInDirectory(final File folder) {
    return folder.listFiles(File::isFile);
  }
```

### List files in directory recursively

```java
  public static List<File> listAllFiles(String path) {
    var all = new ArrayList<File>();
    var list = new File(path).listFiles();

    if (list != null) {  // In case of access error, list is null
      for (var f : list) {
        if (f.isDirectory()) {
          all.addAll(listAllFiles(f.getAbsolutePath()));
        } else {
          all.add(f.getAbsoluteFile());
        }
      }
    }
    return all;
  }
```

### Read lines from file to string list

```java
  public static List<String> readLines(String filename) throws IOException {
    return Files.readAllLines(new File(filename).toPath());
  }
```

### Zip file

```java
  public static void zipFile(String srcFilename, String zipFilename) throws IOException {
    var srcFile = new File(srcFilename);
    try (
            var fileOut = new FileOutputStream(zipFilename);
            var zipOut = new ZipOutputStream(fileOut);
            var fileIn = new FileInputStream(srcFile);
    ) {
      var zipEntry = new ZipEntry(srcFile.getName());
      zipOut.putNextEntry(zipEntry);
      final var bytes = new byte[1024];
      int length;
      while ((length = fileIn.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
      }
    }
  }
```

### Zip multiple files

```java
  public static void zipFiles(String[] srcFilenames, String zipFilename) throws IOException {
    try (
      var fileOut = new FileOutputStream(zipFilename);
      var zipOut = new ZipOutputStream(fileOut);
    ) {
      for (var i=0; i<srcFilenames.length; i++) {
        var srcFile = new File(srcFilenames[i]);
        try (var fileIn = new FileInputStream(srcFile)) {
          var zipEntry = new ZipEntry(srcFile.getName());
          zipOut.putNextEntry(zipEntry);
          final var bytes = new byte[1024];
          int length;
          while ((length = fileIn.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
          }
        }
      }
    }
  }
```

### Zip a directory

```java
  public static void zipDirectory (String srcDirectoryName, String zipFileName) throws IOException {
    var srcDirectory = new File(srcDirectoryName);
    try (
      var fileOut = new FileOutputStream(zipFileName);
      var zipOut = new ZipOutputStream(fileOut)
    ) {
      zipFile(srcDirectory, srcDirectory.getName(), zipOut);
    }
  }
  public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) 
      throws IOException {
    if (fileToZip.isHidden()) { // Ignore hidden files as standard
      return;
    }
    if (fileToZip.isDirectory()) {
      if (fileName.endsWith("/")) {
        zipOut.putNextEntry(new ZipEntry(fileName)); // To be zipped next
        zipOut.closeEntry();
      } else {
        // Add the "/" mark explicitly to preserve structure while unzipping action is performed
        zipOut.putNextEntry(new ZipEntry(fileName + "/"));
        zipOut.closeEntry();
      }
      var children = fileToZip.listFiles();
      for (var childFile : children) { // Recursively apply function to all children
        zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
      }
      return;
    }
    try (
        var fis = new FileInputStream(fileToZip) // Start zipping once we know it is a file
    ) {
      var zipEntry = new ZipEntry(fileName);
      zipOut.putNextEntry(zipEntry);
      var bytes = new byte[1024];
      var length = 0;
      while ((length = fis.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
      }
    }
  }
```

## Math

### Fibonacci

```java
  public static int fibonacci(int n) {
    if (n <= 1) {
      return n;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }
```

### Factorial

```java
  public static int factorial(int number) {
    var result = 1;
    for (var factor = 2; factor <= number; factor++) {
      result *= factor;
    }
    return result;
  }
```

### Haversine formula

```java
  // Radius of sphere on which the points are, in this case Earth.
  private static final double SPHERE_RADIUS_IN_KM = 6372.8;

  public static double findHaversineDistance(double latA, double longA, double latB, double longB) {
    if (!isValidLatitude(latA)
        || !isValidLatitude(latB)
        || !isValidLongitude(longA)
        || !isValidLongitude(longB)) {
      throw new IllegalArgumentException();
    }

    // Calculate the latitude and longitude differences
    var latitudeDiff = Math.toRadians(latB - latA);
    var longitudeDiff = Math.toRadians(longB - longA);

    var latitudeA = Math.toRadians(latA);
    var latitudeB = Math.toRadians(latB);

    // Calculating the distance as per haversine formula
    var a = Math.pow(Math.sin(latitudeDiff / 2), 2)
            + Math.pow(Math.sin(longitudeDiff / 2), 2) * Math.cos(latitudeA) * Math.cos(latitudeB);
    var c = 2 * Math.asin(Math.sqrt(a));
    return SPHERE_RADIUS_IN_KM * c;
  }

  // Check for valid latitude value
  private static boolean isValidLatitude(double latitude) {
    return latitude >= -90 && latitude <= 90;
  }

  // Check for valid longitude value
  private static boolean isValidLongitude(double longitude) {
    return longitude >= -180 && longitude <= 180;
  }
```

### Lottery

```java
  public static Integer[] performLottery(int numNumbers, int numbersToPick) {
    var numbers = new ArrayList<Integer>();
    for(var i = 0; i < numNumbers; i++) {
      numbers.add(i+1);
    }

    Collections.shuffle(numbers);
    return numbers.subList(0, numbersToPick).toArray(new Integer[numbersToPick]);
  }
```

### Greatest Common Divisor

```java
  public static int gcd(int a, int b) { 
    if (b == 0) 
      return a; 
    return gcd(b, a % b);  
  }
```

### Prime

```java
  public static boolean isPrime(int number) {
    if (number < 3) {
      return true;
    }

    // check if n is a multiple of 2
    if (number % 2 == 0) {
      return false;
    }

    // if not, then just check the odds
    for (var i = 3; i * i <= number; i += 2) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }
```

## Media

### Capture screen

```java
  public static void captureScreen(String filename) throws AWTException, IOException {
    var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    var screenRectangle = new Rectangle(screenSize);
    var robot = new Robot();
    var image = robot.createScreenCapture(screenRectangle);
    ImageIO.write(image, "png", new File(filename));
  }
```

## Networking

### HTTP GET

```java
  public static HttpResponse<String> httpGet(String uri) throws Exception {
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .build();
    return client.send(request, BodyHandlers.ofString());
  }
```

### HTTP POST

```java
  public static HttpResponse<String> httpPost(String address, HashMap<String,String> arguments) 
    throws IOException, InterruptedException {
    var sj = new StringJoiner("&");
    for(var entry : arguments.entrySet()) {
      sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
              + URLEncoder.encode(entry.getValue(), "UTF-8"));
    }

    var out = sj.toString().getBytes(StandardCharsets.UTF_8);
    var request = HttpRequest.newBuilder()
            .uri(URI.create(address))
            .headers("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .POST(BodyPublishers.ofByteArray(out))
            .build();

    return HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
  }
```

## String

### Palindrome check

```java
  public static boolean isPalindrome(String s) {
    var sb = new StringBuilder();
    for (var c : s.toCharArray()) {
      if (Character.isLetter(c)) {
        sb.append(c);
      }
    }

    var forward = sb.toString().toLowerCase();
    var backward = sb.reverse().toString().toLowerCase();
    return forward.equals(backward);
  }
```

### Reverse string

```java
  public static String reverseString(String s) {
    return new StringBuilder(s).reverse().toString();
  }
```

### String to date

```java
  public static Date stringToDate(String date, String format) throws ParseException {
    var simpleDateFormat = new SimpleDateFormat(format);
    return simpleDateFormat.parse(date);
  }
```

### Anagram Check

```java
  public boolean isAnagram(String s1, String s2) { 
    var l1 = s1.length();
    var l2 = s2.length();
    var arr1 = new int[256];
    var arr2 = new int[256];
    if (l1 != l2) {
      return false;
    }
    
    for (var i = 0; i < l1; i++) {
      arr1[s1.charAt(i)]++;
      arr2[s2.charAt(i)]++;
    }

    return Arrays.equals(arr1, arr2);
  }
```

### Find Levenshtein distance

```java
  public static int findLevenshteinDistance(String word1, String word2) {
    // If word2 is empty, removing
    int[][] ans = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i <= word1.length(); i++) {
      ans[i][0] = i;
    }

    // if word1 is empty, adding
    for (int i = 0; i <= word2.length(); i++) {
      ans[0][i] = i;
    }

    // None is empty
    for (int i = 1; i <= word1.length(); i++) {
      for (int j = 1; j <= word2.length(); j++) {
        int min = Math.min(Math.min(ans[i][j - 1], ans[i - 1][j]), ans[i - 1][j - 1]);
        ans[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? ans[i - 1][j - 1] : min + 1;
      }
    }
    return ans[word1.length()][word2.length()];
  }
```

### Compare Version

```java
  public static int compareVersion(String v1, String v2) {
    Function<String, String[]> getVersionComponents = version -> version.replaceAll(".*?((?<!\\w)\\d+([.-]\\d+)*).*", "$1", "$1").split("\\.");

    var components1 = getVersionComponents.apply(v1);
    var components2 = getVersionComponents.apply(v2);
    int length = Math.max(components1.length, components2.length);

    for (int i = 0; i < length; i++) {
      Integer c1 = i < components1.length ? Integer.parseInt(components1[i]) : 0;
      Integer c2 = i < components2.length ? Integer.parseInt(components2[i]) : 0;
      int result = c1.compareTo(c2);
      if (result != 0) {
        return result;
      }
    }
    return 0;
  }
```

## Class

### Get methods name

```java
  public static List<String> getAllMethods(final Class<?> cls) {
    return Arrays.stream(cls.getDeclaredMethods())
            .map(Method::getName)
            .collect(Collectors.toList());
  }
```

### Get public field names

```java
  public static List<String> getAllFieldNames(final Class<?> cls) {
    return Arrays.stream(cls.getFields())
            .map(Field::getName)
            .collect(Collectors.toList());
  }
```

### Get all field names

```java
  public static List<String> getAllFieldNames(final Class<?> cls) {
      var fields = new ArrayList<String>();
      var currentCls = cls;
      while (currentCls != null) {
        fields.addAll(
            Arrays.stream(currentCls.getDeclaredFields())
                .filter(field -> !field.isSynthetic())
                .map(Field::getName)
                .collect(Collectors.toList()));
        currentCls = currentCls.getSuperclass();
      }
      return fields;
    }
```

### Create object

```java
  public static Object createObject(String cls)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            ClassNotFoundException {
    var objectClass = Class.forName(cls);
    var objectConstructor = objectClass.getConstructor();
    return objectConstructor.newInstance();
  }
```


## I/O

### Read file by stream

```java
  public static List<String> readFile(String fileName) throws FileNotFoundException {
    try (Stream<String> stream = new BufferedReader(new FileReader(fileName)).lines()) {
      return stream.collect(Collectors.toList());
    }
  }
```

### InputStream to String

```java
  public static String inputStreamToString(InputStream inputStream) throws IOException {
    try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {
      var stringBuilder = new StringBuilder();
      var data = reader.read();
 
      while (data != -1) {
        stringBuilder.append((char) data);
        data = reader.read();
      }
      return stringBuilder.toString();
    }
  }
```


## Thread

### Create pool of threads

```java
  public static ExecutorService createFixedThreadPool() {
    return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }
```

