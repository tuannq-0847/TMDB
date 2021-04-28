import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hello_world/src/resources/api_repository.dart';
import 'package:hello_world/src/ui/movies.dart';

class App extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData.dark(),
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(title: const Text("Movies")),
        body: MoviesScreen(),
        bottomNavigationBar:
            BottomNavigationBar(items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Movies',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.business),
            label: 'Favorite',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.school),
            label: 'Search',
          ),
        ], selectedItemColor: Colors.amber[800]),
      ),
    );
  }
}
