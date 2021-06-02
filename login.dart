import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter/services.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: "Demo App",
      home: new MyHome(),
    );
  }
}

class MyHome extends StatefulWidget {
  @override
  State createState() {
    return MyHomeState();
  }
}

class MyHomeState extends State<MyHome> {
  TextEditingController userController = new TextEditingController();
  TextEditingController passController = new TextEditingController();

  bool _isVisible = true;
  bool _isValidUser = true;
  bool _isValidPass = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: new Container(
        decoration: BoxDecoration(
          color: Colors.lightGreenAccent[100],
        ),
        child: Padding(
          padding: const EdgeInsets.fromLTRB(32, 32, 32, 0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              SizedBox(height: 140,),
              Image.asset(
                "images/anh.jpg",
                scale: 3,
                fit: BoxFit.fill,
              ),
              SizedBox(
                height: 30,
              ),
              Padding(
                padding: const EdgeInsets.fromLTRB(0, 0, 15, 25),
                child: Column(
                  children: [
                    TextField(
                      controller: userController,
                      decoration: InputDecoration(
                        icon: Icon(Icons.email_outlined),
                        labelText: "Username",
                        errorText: _isValidUser ? null : "Invalid email",
                        hintText: "abc@gmail.com",
                      ),
                      keyboardType: TextInputType.emailAddress,
                      onChanged: (value) {
                        setState(() {
                          String pattern = r"^[a-zA-Z]\w+\@\w+\.\w+(\.\w+)?$";
                          _isValidUser = RegExp(pattern).hasMatch(value) ? true : false;
                        });
                      },
                    ),
                    TextField(
                      obscureText: !_isVisible,
                      controller: passController,
                      decoration: InputDecoration(
                        icon: Icon(Icons.lock),
                        labelText: "Password",
                        errorText: _isValidPass ? null : "Invalid password",
                        suffixIcon: IconButton(
                          icon: Icon(
                              _isVisible ? Icons.visibility : Icons.visibility_off),
                          onPressed: () => setState(() => _isVisible = !_isVisible),
                        ),
                      ),
                      onChanged: (value) {
                        setState(() {
                          _isValidPass = value.trim().isNotEmpty ? true : false;
                        });
                      },
                    ),
                  ],
                ),
              ),
              SizedBox(
                child: ElevatedButton(
                  onPressed: () {
                    print("Login ${userController.text} and ${passController.text}");
                  },
                  child: new Text("Login"),
                  style: ElevatedButton.styleFrom(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20.0),
                      //
                    ),
                    side: BorderSide(style: BorderStyle.solid),
                  ),
                ),
                width: 280,
                height: 40,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
