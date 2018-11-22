package com.bfirestone.udacity.popularmovies.database.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.bfirestone.udacity.popularmovies.Utils.AppExecutors;
import com.bfirestone.udacity.popularmovies.database.AppDatabase;
import com.bfirestone.udacity.popularmovies.database.dao.MovieDao;
import com.bfirestone.udacity.popularmovies.database.entity.MovieEntity;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private AppExecutors appExecutors;

    private LiveData<List<MovieEntity>> movies;

    public MovieRepository(Application application) {
        movieDao = AppDatabase.getInstance(application).movieDao();
        movies = movieDao.loadAllFavoriteMovies();
        appExecutors = AppExecutors.getExecutorInstance();
    }

    public LiveData<List<MovieEntity>> loadAllMovieFaves() {
        return movies;
    }

    public boolean isFavorite(int movieId) {
        MovieEntity movieEntity = movieDao.getMovieById(movieId);
        return movieEntity != null;
    }

    public void addMovieFave(MovieEntity movieEntity) {
        appExecutors.getDiskIO().execute(() -> movieDao.insertFavoriteMovie(movieEntity));
    }

    public void deleteMovieFave(MovieEntity movieEntity) {
        appExecutors.getDiskIO().execute(() -> movieDao.deleteFavoriteMovie(movieEntity));
    }
}