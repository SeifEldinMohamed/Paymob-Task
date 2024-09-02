# Paymob-Task (Movies App)
# Description
An application that displays a list of the best movies in 2024. Each 
movie should be clickable, leading to a details page containing all relevant information. 

# TMDB API Details
  - API Base URL: https://api.themoviedb.org/3/
  - Image Base URL: https://image.tmdb.org/t/p/w500/
1. Get Popular Movies
  - Description: Fetch a list of popular movies.
  - Endpoint: /movie/popular
  - Example: /movie/popular
  **Parameters:**
    - page: (Optional) The page of results to return (default is 1).
    - language: (Optional) Specify the language of the results. Example: en-US.
    - region: (Optional) Specify the region to filter the results by country. Example: US.
    
2. Get Movie Details
    - Description: Fetch details about a specific movie.
    - Endpoint: /movie/{movie_id}
    - Example: /movie/123 (where 123 is the movie ID)
  Parameters:
    - movie_id: (Required) The ID of the movie.
    - language: (Optional) Specify the language of the results. Example: en-US.
    - append_to_response: (Optional) Additional requests to append to the result. Example: videos,images.

# Built with:
• Kotlin<br />
• Compose<br />
• MVVM Clean Architecture<br />
• Retrofit<br />
• OKHTTP3<br />
• Gson<br />
• Hilt<br />
• Coroutines<br />
• State Flow<br />
• Coil<br />
• ROOM<br />
• Paging 3<br />
• Chucker<br />
• Lottie Animation<br />
• Junit<br />
• Mockk<br />
• Turbine<br />


# Screen Shots
<p align="center">
<img src = "screenshots/trending_light.jpg"  height="400" width = "200">
<img src = "screenshots/details_light.jpg" height="400" width = "200">
<img src = "screenshots/issues_light.jpg" height="400" width = "200">
<br />
<br />
<img src = "screenshots/shimmer_light.jpg"  height="400" width = "200">
<img src = "screenshots/error_light.jpg" height="400" width = "200">
<br />
<br />
<br />
<br />
<img src = "screenshots/trending_dark.jpg"  height="400" width = "200">
<img src = "screenshots/details_dark.jpg" height="400" width = "200">
<img src = "screenshots/issues_dark.jpg"  height="400" width = "200">
<br />
<br />
<img src = "screenshots/shimmer_dark.jpg" height="400" width = "200">
<img src = "screenshots/error_dark.jpg"  height="400" width = "200">
</p>
