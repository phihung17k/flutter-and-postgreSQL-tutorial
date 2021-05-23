- ```dart
  main(){
      print('abc');
  }
  ```
  
  - `main()`: entry point of application
  
  - `print()`: print string or value

- Identifier: the name of variable, function, etc
  
  - Identifier can include character and digit (not begin with a digit)
  
  - Identifier don't include special symbols except undercore `_` or dollar `$`

- Keywords: dart ignores space, tabs and newlines

- Dart is Case-sensitive

- End of statement must end with a semicolon `;`

- Comment: single-line `//` and multi-line `/* */`

- Dart don't support `public, protected, private`. If `identifier` start with `_`, nó là `private` trong `library` của nó. Mỗi file `.dart` được coi là 1`library` 

- Dart is an Object-Orient language. Mọi thứ gán cho biến là object
  
  ```dart
  class MyClass{
      void hello(){
          print("abc");
      }
  }
  void main(){
      MyClass c = new MyClass();
      c.hello();
  }
  ```

**Data types**

- Number
  
  - Integer: non-fractional value, `int` keyword
  
  - Double: fractional value, `double` keyword, 64 bit

- String
  
  - String is a sequence of characters of UTF-16 code units
  
  - Runes are a sequence of UTF-32 code unit
  
  - Use by single `'abc'` or double `"abc"` quotes

- Boolean
  
  - True of false, `bool` keyword

- List and Map
  
  - Collection of objects
  
  - `List` is an ordered group of objects
  
  - `Map` is a set of values as key-value pairs
  
  - Import `dart: core`

- Runers (hiển thị các ký hiệu UNICODE trong string, như emotion)

- Symbols

- Dynamic type
  
  - Optional type
  
  - If type of variable is not specified, its type is `dynamic`
  
  - keyword `dynamic`

**Variable**

- Use `var` to declare, Dart tự suy ra type
  
  ```dart
  var name = 'abc';
  int num = 1;
  String name = 'abc';
  ```

- All values is object, so the initial value = `null`

- Declare variable = null, use `?`: `int? x = null`

- Truy cập giá trị null, use `?.`
  
  - ```dart
    //nếu foo là null, bar() không được gọi và a = null
    var a = foo?.bar();
    //sử dụng trong 1 line
    foo?.bar?.baz();
    ```

- Use `dynamic` for variable ( or `Object`), nếu không muốn giới hạn bởi bất cứ kiểu gì 
  
  ```dart
  dynamic x = 1;
  x = 'tom';        //dynamic for any type
  print(x);            //tom
  ```

- Use `final` and `const` to declare constants. Don't modify the `final` or `const` variables. These keyword can use with the variable's data type, not `var` keyword. The `const` keyword represent a compile-time constant, implicitly final.
  
  Các biến được khai báo là property của class chỉ có thể là `final`, ko phải`const`
  
  ```dart
  final variable;
  final data_type variable;
  //or
  const variable
  const data_type variable;
  //ex
  void main() { 
     final val1 = 12; 
     print(val1);            //12
     const pi = 3.14; 
     const area = pi*12*12; 
     print("The output is ${area}"); //The output is 452.15999999999997
  }
  //có thể tạo constant cho value
  //vd tạo 1 list rỗng không thể thay đổi - Empty Immutable List (EIL)
  var foo = const [];      // foo is an EIL  
  final bar = const [];    // bar will always be an EIL
  const baz = const [];    // baz is a compile-time constant EIL
  ```

**Operators**

- Arithmetic Operator: `+`, `-`, `*`, `/`, `~/` (divide return integer), `%`, `++`,`--`

- Equality and relational operator: `>, <, >=, <=, ==, !=`
  
  - Type test: `is` - true, `is!` - false, kiểm tra type giống `instance of` trong Java

- Bitwise
  
  | Operator    | Description | Example                                                               |
  | ----------- | ----------- | --------------------------------------------------------------------- |
  | AND         | a & b       | return 1 if 2 operands are 1                                          |
  | OR          | a \| b      | return 1 if 2 operands are (1 and 0) or (1 and 1)                     |
  | XOR         | a ^ b       | return 1 if 2 operands are 1 and 0, 0 and 1                           |
  | NOT         | ~ a         | invert the bits                                                       |
  | left shift  | a << b      | shift binary to left and add 0 to the right                           |
  | right shift | a >> b      | shift binary to right (remove the shift binary) and add 0 to the left |

- Assignment operator: `=`, `??=` (assign value if variable = null), `+=`, `-=`, `*=`, `/=`
  
  The same logic to Bitwise: <<=, >>=, |=, ^=

- Logical Operators: `&&`, `||`, `!`

- Conditional Expression
  
  - `condition ? expr1 : expr2` toán tử 3 ngôi
  
  - `a ?? b` : if a is not-null, return a, else return b. Nếu cả 2 null, return null

- Cascade notation `..`: thực hiện nhiều operation trên cùng 1 object
  
  ```dart
  var button = querySelector('#confirm');
  button.text = 'Confirm';
  button.classes.add('important');
  button.onClick.listen((e) => window.alert('Confirmed!'));
  //use cascade
  querySelector('#confirm')    //get an object
      ..text = 'Confirm'    
      ..classes.add('important')
      ..onClick.listen((e) => window.alert('Confirmed!'));
  ```

**Loops**

- Define the size: `for`,`foreach`

- Undefine: `while`, `do while`

- Exit the loop: `break`

- Skip a loop: `continue`

- ```dart
  void main() { 
     outerloop: // This is the label name  
     for (var i = 0; i < 5; i++) { 
        print("Innerloop: ${i}"); 
        innerloop: 
  
        for (var j = 0; j < 5; j++) { 
           if (j > 3 ) break ; 
  
           // Quit the innermost loop 
           if (i == 2) break innerloop; 
  
           // Do the same thing 
           if (i == 4) break outerloop; 
  
           // Quit the outer loop 
           print("Innerloop: ${j}"); 
        } 
     } 
  }//0 0 1 2 3 1 0 1 2 3 2 3 0 1 2 3 4
  ```

**Decision Making**

- `if`, `if else`, `switch case`
  
  ```dart
  switch(expression){
      case ... : {
  
      }
      break;
      case ... : {
  
      }
      break;
      default: {
  
      }
      break;
  }
  ```

**Numbers**

- `int`, `double`

- If the fractional values are assigned to integer, throw exception

- Parsing: use static func `parse()` to parse `string` to `numberic`
  
  - Throws `FormatException` if pass the value != numberic
  
  - ```dart
    print(num.parse('12'));    //12
    print(num.parse('10.5'));    //10.5
    ```

- Number properties: `hashcode`, `isFinite`, `isInfinite`, `isNan`, `isNegative`, `sign`(dấu), `isEven`, `isOdd`

- Number methods: `abs`, `ceil`, `compareTo`, `Floor`, `remainder`, `Round`, `toDouble`, `toInt`, `toString`, `truncate`(return integer from fractional number)

**Strings**

- Dart use UTF 16

- Represent by single, double or triple quotes. Triple quotes for multiple line
  
  ```dart
  //1 line
  String a = 'value';    
  String a = "value"; 
  //multi line    
  String a = '''line1    
  line2''';               
  String a = """line1 
  line2""";
  //raw string: hiển thị đúng những gì đã ghi. \n ko phải là xuống dòng
  //đặt `r` trước
  var s = r"In the sea, \n We has";
  ```

- ```dart
  String a = "a";
  String b = "b";
  String c = a+b;
  print("${c}");    //ab
  ```

- Use `$variable` or `${expression}` to get value
  
  ```dart
  String a = '1';
  print("$a");    //1
  print("${2+2}");    //4    
  ```

- String properties: `codeUnits`- return a array of UTF-16 code, `isEmpty`, `Length`

- String methods - `dart: core` library: `toLowerCase()`, `toUpperCase()`, `trim()`, `compareTo()`, `replaceAll()`, `split()`, `substring()`, `toString()`, `codeUnitAt()`- return 16 bit UTF-16 code at the given index

**Boolean**

- 2 value: true and false, use `bool` = true or false

- ```dart
  void main(){
      bool a;
      a = 12 > 5;
      print(a);    //true
  }
  ```

**Lists**

- `List` object is an ordered group, use `dart:core` library, arrays là `List` object

- The first index = 0, the last index = n-1 in a `List` n elements

- List can be classified (phân loại) as:
  
  - Fixed Length List
  
  - Growable List

- <u>Fixed Length List</u>
  
  - Cannot change at runtime. Throw exception if grow(mở rộng) or shrink(co lại)
  
  - ```dart
    var list_name = new List(init_size);
    list_name[index] = value;
    ...
    ```
  
  - ```dart
    var l = new List(3);
    l[0] = 1;
    l[1] = 2;
    l[2] = 3;
    //l[3] = 4;        //this line throw exception
    print(l);    //[1, 2, 3]
    ```

- <u>Growable List</u>
  
  - Can change at runtime
  
  - ```dart
    var list = [value1, value2, value3];
    //or
    var list = new List();
    //then initalizing the list
    list.add(value)
    ```

- Listproperties: `first`, `isEmpty`, `isNotEmpty`, `length`, `last`, `reversed`, `Single`

- List methods: add, addAll, list[index]=..., replaceRange(), remove(), removeAt(), removeRange(), ...

**Map**

- Use `{ }` and Constructor, can include NULL

- ```dart
  var map = {key1:value1, key2:value2, ...};
  ```

- ```dart
  var map = new Map();
  map[key] = value;    //add key: value
  //ex
  var m = new Map();
  m["a"] = "admin";
  print(m);    //{a: admin}
  ```

- Map properties: `Keys` - all keys, `Values` - all values, `Length`,`isEmpty`, `isNotEmpty`

- Map func: `addAll()`, `clear()`, `remove()`, `forEach()` 

**Rune**

- Rune is an integer representing a Unicode code UTF-16, import `dart:core`

- Access in 3 ways:
  
  - `String.codeUnitAt(int index)` function return 16-bit UTF-16 code
  
  - `String.codeUnits` property: return a list of UTF-16 code units of string
  
  - `String.runes` property: return a list UTF-16  code

- ```dart
  import 'dart:core';
  void main(){
      String x = 'Rune';
      print(x.codeUnitAt(0));    //R: 82
      print(x.codeUnits);        //[82, 117, 110, 101]
      print("Hai ba".runes);    //(72, 97, 105, 32, 98, 97)
  }
  ```

- Unicode use as `u\XXXX`, where XXXX is a 4-digit hexadecimal value. To specify less or more than 4 digit, use `${ }`, can use constructor of Runes

- ```dart
  Runes i = new Runes('\u042E');    //\u{1f605} ==> 😅
  print(i);    //(1070)
  print(new String.fromCharCodes(i))    //Ю
  ```

**Enum**

- An enumeration: xác định các giá trị hằng được đặt tên

- ```dart
  enum enum_name {
      constant value1,
      constant value2,
      constant value3,
  }
  ```

- The first index = 0

- ```dart
  enum Status { 
     none, 
     running, 
     stopped, 
     paused 
  }  
  void main() { 
     print(Status.values); 
     Status.values.forEach((v) => print('value: $v, index: ${v.index}'));
     print('running: ${Status.running}, ${Status.running.index}'); 
     print('running index: ${Status.values[1]}'); 
  }
  //result
  /*
  [Status.none, Status.running, Status.stopped, Status.paused] 
  value: Status.none, index: 0 
  value: Status.running, index: 1 
  value: Status.stopped, index: 2 
  value: Status.paused, index: 3 
  running: Status.running, 1 
  running index: Status.running */
  ```

**Function**

- Function được coi như là object, tất cả function đều return value, nếu không có return thì **default return null**

- ```dart
  bool isEven(int num){
      return num%2==0 ;
  }
  //có thể bỏ type, not recommend
  isEven(int num){
      return num%2 == 0;
  }
  //if func has only 1 expression
  //use => expression instead of {return expression;}
  //không áp dụng cho statement, vd if else, switch case
  isEven(int num) => num%2 == 0;
  ```

- Function has 2 type of parameter: required and optional
  
  - All required parameter **must be defined before** the optional parameter

- Optional Positional Parameter
  
  - Tham số không bắt buộc cho vào `[ ]`, vị trí tham số trùng khớp
  
  - Giá trị của tham số không bắt buộc có thể `null`
  
  - ```dart
    void main(){
        test(123);        //123    null
        test(123, 'ko');    //123    ko
    }
    test(n1, [s1]){
        print(n1);
        print(s1);
    }
    ```

- Optional named parameter
  
  - Tham số không bắt buộc cho vào `{ }`, tên tham số trung nhau
  
  - The parameter's name **must** be specificed while its value is passed
  
  - ```dart
    void main(){
        test(123);        //123   null    null
        test(123, s1:'mot');    //123    mot    null
        test(123, s2:'mot', s1:'hai');    //123    hai    mot
    }
    test(n1, {s1, s2}){
        print(n1);
        print(s1);
        print(s2);
    }
    ```

- Optional Parameter with Default values
  
  - The optional parameter put in `{para = value, para: value}`
  
  - ```dart
    void main(){
        test(123);    //123    12    13
    }
    test(n1, {s1 = 12, s2 : 13}){
        print(n1);
        print(s1);
        print(s2);
    }
    ```
  
  - Có thể gán List hay Map là giá trị default bằng `const`
  
  - ```dart
    void main() { 
       test_param(123); 
    }  
    void test_param(n1,{s1= const [1, 2, 3],
                        s2: const {'mot': 1,'hai': 2 } }) { 
       print(n1);    //123
       print(s1);     //[1, 2, 3]
       print(s2);    //{mot: 1, hai: 2}
    } 
    ```

- Recursive function: có thể dùng đệ quy funtion

- Có thể truyền function như parameter của 1 function khác. Có thể gán function cho biến có kiểu trả về giống nhau

- ```dart
  void main() { 
     var x = sum(5, 6);    //assign to variable
    print("x = $x");
    print(sum(7, 2));    //pass sum as parameter of print()
  }  
  sum(a, b) => a+b;
  ```

**Anonymous Function**

- Hầu hết các func đều có tên như main(), print(). Những function không có tên gọi là <i>anonymous function</i>, có thể gán cho variable, hay add vào 1 collection

- ```dart
  ([type] param1[, ...]){
      statement;
  };
  //nếu anonymous function chỉ chưa 1 statement
  // có thể dùng => như lamda expression
  ([type] param) => statement;
  ```

- Example
  
  ```dart
  var list = [1, 2, 3];
  list.forEach((item){
      print('${list.indexOf(item)}: $item');
  });
  //or
  list.forEach((item) => print('${list.indexOf(item)}: $item'));
  ```

**Interface**

- Interface define a set of methods , Dart don't have a syntax for declare interface

- Class `implements` interface, must redefine all methods of that interface 

- ```dart
  void main() {
      B b = new B();
      b.doB();        //do B
  }
  class A {
      void doA(){
          print("do A");
      }
  }
  class B implements A{
      void doA(){
          print("do B");
      }
  }
  ```

- Implement multiple interface `class B implements A, C, D {}`

**Class**

- ```dart
  class class_name {
      //fields: variable
      //getter, setter
      //constructors
      //functions/ methods
  }
  ```

- Create an instance of class `var car = new Car(...)`

- Access properties or methods, use `.`,  ex:`car.color`

**Constructor**

- Constructor cannot have a return type. Don't declare constructor, a default no-argument constructor is used

- ```dart
  class Car{
      Car(String color){
         ...
      }
  }
  ```

- Named Constructor: enable a class define mutiple constructors - like overload
  
  ```dart
  class Car{
      Car(){}    //default constructor
      Car.namedColor(String color){    //named constructor
          ...
      }
  }
  void main(){
      Car c1 = new Car();
      Car c2 = new Car.namedColor('blue');
  }
  ```

- Use `this` like Java

**Getter, setter**

- ```dart
  class Car{
      String name;
      
      String get car_name{        //keyword get
          return name;
      }
      void set car_name(String name){        //keyword set
          this.name = name;
      }
  }
  ```

**Inheritance**

- Keyword `extends`. Child inherit all properties and methods except parent's constructor

- Dart doesn't support multiple inheritance

- ```dart
  void main(){
      A b = new B();
      b.display();    //doB
      A c= new C();
      c.display();    //doA vì C không redefine lại method display của A
  }
  class A{
      void display() => print("doA");
  }
  class B extends A{
      void display() => print("doB");
  }
  class C extends A{
  }
  ```

- 3 loại inheritance: single, multiple, multi-level

- Redefine methods use `@override`

- `static` is the same Java

- `super`  refer to the parent of class, to get variable, property, methods
