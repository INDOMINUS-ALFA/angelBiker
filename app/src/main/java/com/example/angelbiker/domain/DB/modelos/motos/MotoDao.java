package com.example.angelbiker.domain.DB.modelos.motos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MotoDao {
    //insert cuerry

    @Insert(onConflict = REPLACE)
    void insert(Moto moto);

    //Delete querry

    @Delete void delete(Moto moto);


    //Update query
    @Query("UPDATE Motos SET precio = :sPrecio Where nombre = :sNombre")
    void update (int sPrecio, String sNombre);

    @Query("DELETE  FROM Motos")
    void deleteAll();



    // select

    @Query("SELECT nombre, miniatura FROM Motos WHERE marca = :smarca ORDER BY precio ASC")
    LiveData<List<MotoMinimal>> getAlphabetizedMotos(String smarca);


    @Query("SELECT * FROM Motos WHERE nombre = :snombre ORDER BY precio ASC")
     Moto motoSelecionada(String snombre);

}
