- Widgets describe their view about configuration and state, những thành phần nhỏ và có thể sử dụng lại, xây dựng giao diện dựa trên các widget

- ```java
  import 'package:flutter/material.dart';
  
  void main() {
    runApp(
      Center(
        child: Text(
          'Hello, world!',
          textDirection: TextDirection.ltr,
        ),
      ),
    );
  }
  ```

- The `runApp()` make the given Widget the root of the widget tree. In above example, the widget tree consist of 2 widgets: `Center` and its child `Text` widget.

- A `SafeArea` widget is also used to properly pad the text so it appears below the display on the top of the screen.

- The new widget is usually the subclass of either `StatelessWidget` or `StatefulWidget` . The widget's main job is to implement a `build()` function

**Basic widgets**

- A suite of powerful basic widgets :
  
  - `Text`: the text widget to create text and its style 
  
  - `TextInput`: nhận input
  
  - `Image`: display image
  
  - `Icon`: display 1 icon (Material)
  
  - `Row, Column`:  hiển thị list of widget theo chiều ngang (horizontal) hay dọc (vertical) 
  
  - `Stack`:  hiển thị list phần tử con bên trên 1 phần tử khác, giống `position` trong CSS
    
    - Using the `Positioned` widget on children of `Stack` to position them (top, right, bottom, left) (optional). 
  
  - `Container`: 
    
    - create a rectangular element,
    
    - is decorated with a `BoxDecoration` such as background, border, shadow 
    
    - have margin, padding, constraints to size

- `Scaffold`: 1 widget cha ngoài cùng của toàn bộ các page trong app, đưa ra các layout cơ bản (navigation, appbar, back button, ...)

- <u>Note</u>: fields in the subclass of Widget are always marked `final`

- To use `Material design`, ensure have a `use-material-design: true` in `flutter` section of `pubspec.yaml` file
  
  ```yaml
  name: my_app
  flutter:
      uses-material-design: true
  ```

- Tất cả phần từ đều là widget

- Example:
  
  - Display 1 text: `const Text("Hello")`
  
  - Display a button: `const Button(onTap: ...)`
  
  - Display a backgroud color: `const BoxDecoration(background: Colors.blue)`

- Các widget thuộc 1 trong 2 loại: `Stateless`(ko có state), Statefull (có state)
  
  - `Stateless`
    
    - <img title="" src="file:///E:/OJT/Flutter/img/stateless.png" alt="" width="484">
    
    - Widget cố định (Text, ...), không thay đổi giá trị trong suốt lifecycle. Chỉ render 1 lần vào lúc build
    
    - Có 1 class: widget (build)
    
    - ```java
      class BigText extends StatelessWidget{
          final String text;
          //constructor
          BigText(this.text);
      
          Widget build(context){
              return new Text(
                  text,
                  textStyle: new TextStyle(fontSize: 20.0),
              );
          }
      }
      ```
  
  - `Stateful`
    
    - <img src="file:///E:/OJT/Flutter/img/statefull.png" title="" alt="" width="486">
    
    - Widget xây dựng sẵn, render lại UI khi state(dữ liệu) thay đổi
    
    - Có 2 class: state và widget 
    
    - ```java
      class ShoppingList extends StatefullWidget {
          ShoppingList({Key key, this.products}) : super(key:key);
          final List<Product> products;
          
          @override
          _ShoppingListState createState() => _ShoppingListState();
      }
      
      
      class _ShoppingListState extends State<ShoppingList> {
          ...
          @override 
          Widget build(BuildContext context{
              return Scaffold(
                  appBar: AppBar(
                      ...
                  )
              )
          }
      }
      
      ```
    
    - 
