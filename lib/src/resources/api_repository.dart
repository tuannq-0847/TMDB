import 'package:dio/dio.dart';
import 'package:hello_world/src/models/item_poplular.dart';
import 'package:hello_world/src/resources/dio_client.dart';

class APIRepository {
  DioClient dioClient;
  static final String _baseURL = "https://api.themoviedb.org/3/";

  APIRepository() {
    var dio = Dio();
    dioClient = DioClient(_baseURL, dio);
  }

  Future<MovieResponse> fetchMovieList() async {
    final response = await dioClient
        .get("movie/popular", queryParameters: {"api_key": "1f54bd990f1cdfb230adb312546d765d"});
    print("response fetchMovieList: $response");
    return Future.value(MovieResponse.fromJson(response));
  }
}
