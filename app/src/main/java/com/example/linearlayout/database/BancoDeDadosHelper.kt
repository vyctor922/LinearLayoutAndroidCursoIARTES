package com.example.linearlayout.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoDeDadosHelper(context: Context) : SQLiteOpenHelper(
    context,
     DATABASE_NAME,
    null, DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_NAME = "database_usuarios.db"
        private const val DATABASE_VERSION = 1

        // Nome da tabela
        private const val TABLE_USERS = "usuarios"

        // Colunas da tabela
        const val COLUMN_ID = "Id"
        const val COLUMN_EMAIL = "Email"
        const val COLUMN_PASSWORD = "Senha"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EMAIL TEXT NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL
                );
        """.trimIndent()
        db?.execSQL(createTableQuery)

        val initialUsers = listOf(
            mapOf(
                COLUMN_EMAIL to "lucas@email.com",
                COLUMN_PASSWORD to "123456"
            ),
            mapOf(
                COLUMN_EMAIL to "fulano@email.com",
                COLUMN_PASSWORD to "654321"
            ),
            mapOf(
                COLUMN_EMAIL to "joao@abc.com",
                COLUMN_PASSWORD to "abcdef"
            )
        )

        initialUsers.forEach( { user ->
            val values = ContentValues().apply {
                put(COLUMN_EMAIL, user[COLUMN_EMAIL])
                put(COLUMN_PASSWORD, user[COLUMN_PASSWORD])
            }
            db?.insert(TABLE_USERS, null, values)
        })
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertUser(email: String, password: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        return db.insert(TABLE_USERS, null, values)
    }

}