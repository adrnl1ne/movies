package com.example.movieworkshop.reposetory;


import com.example.movieworkshop.model.Movie;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MovieRepository {
    private List<Movie> movies = new ArrayList<>();
    private Connection conn = DatabaseConnectionManager.getConnection();

    public List<Movie> readAll() {

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM movies");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                movies.add(new Movie(
                                resultSet.getString("year"),
                                resultSet.getString("length"),
                                resultSet.getString("title"),
                                resultSet.getString("subject"),
                                resultSet.getString("popularity"),
                                resultSet.getString("awards"),
                                resultSet.getInt("id")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }


    // Get one movie based on the id
    public Movie read(int id) {
        try {
            PreparedStatement psts = conn.prepareStatement("SELECT * FROM movies WHERE id = ?");
            psts.setInt(1, id);
            ResultSet resultSet = psts.executeQuery();

            if (resultSet.next()) {
                return new Movie(
                        resultSet.getString("year"),
                        resultSet.getString("length"),
                        resultSet.getString("title"),
                        resultSet.getString("subject"),
                        resultSet.getString("popularity"),
                        resultSet.getString("awards"),
                        resultSet.getInt("id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

}


