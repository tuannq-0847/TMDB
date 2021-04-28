import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hello_world/src/models/item_poplular.dart';
import 'package:hello_world/src/resources/api_repository.dart';

class MoviesScreen extends StatelessWidget {
  APIRepository _apiRepository = APIRepository();

  // ItemPopular _getItemPopular() {
  //   ItemPopular itemPopular;
  //   _apiRepository.fetchMovieList().then((value) => itemPopular = value);
  //   return itemPopular;
  // }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
          body:
          FutureBuilder(
            future: _apiRepository.fetchMovieList(),
            builder: (context, snapshot) {
              // operation for completed state
              if (snapshot.connectionState == ConnectionState.done) {
                return ListView.builder(
                    itemCount: (snapshot.data as MovieResponse).results.length,
                    itemBuilder: (context, index) {
                      var item = (snapshot.data as MovieResponse).results[index];
                      print("mother fcker: $item");
                      return ListTile(
                        title: Text('${item.title}'),
                        leading: Container(
                          constraints: BoxConstraints.tightFor(width: 100.0),
                          child: Image.network("https://image.tmdb.org/t/p/w500${item.backdropPath}",fit:
                            BoxFit.fitWidth,),
                        ),
                      );
                    });
              }

              // spinner for uncompleted state
              return Center(
                child: Container(
                    alignment: AlignmentDirectional.bottomCenter,
                    child: Column(
                      children: <Widget>[
                        CircularProgressIndicator(),
                      ],
                      mainAxisAlignment: MainAxisAlignment.spaceAround,
                      crossAxisAlignment: CrossAxisAlignment.center,
                    )),
              );
            },
          ),
      ),
    );
  }
}
