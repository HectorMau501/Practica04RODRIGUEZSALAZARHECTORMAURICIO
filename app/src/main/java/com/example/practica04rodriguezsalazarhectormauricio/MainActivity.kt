package com.example.practica04rodriguezsalazarhectormauricio

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    //Tamanio del arreglo
    val tamanio = 10

    //Declaracion de arreglo de 10 posiciones, inicializando en nulo
    //El simbolo ? representa que acepte valores nulos
    var motocicleta1 = Array<Motocicleta?>(tamanio) {null}

    //Este mapa se utiliza comúnmente para almacenar datos asociados con un identificador único
    //(en este caso, un entero) para que puedas buscar y recuperar esos datos
    //de manera eficiente cuando sea necesario.
    private val motocicletas = mutableMapOf<Int, Motocicleta>()

    //Declaracion de la clase
    //private lateinit var objMotocicleta: Motocicleta

    //Declaracion de los componentes graficos
    private lateinit var titulo: TextView
    private lateinit var name: EditText
    private lateinit var type: AutoCompleteTextView
    private lateinit var year: EditText
    private lateinit var height: EditText
    private lateinit var ID: EditText
    private lateinit var gasoline: EditText
    private lateinit var description: EditText
    private lateinit var price: EditText
    private lateinit var buscar: EditText

    //Botones
    private lateinit var add: Button
    private lateinit var seach: Button
    private lateinit var clean: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Asociamos los componentes
        titulo = findViewById(R.id.textTitulo)
        name = findViewById(R.id.editNombre)
        type = findViewById(R.id.autoCompleteTipo)
        year = findViewById(R.id.editAnio)
        height = findViewById(R.id.editAltura)
        ID = findViewById(R.id.editId)
        gasoline = findViewById(R.id.editGasolina)
        description = findViewById(R.id.editDescripcion)
        price = findViewById(R.id.editPrecio)
        buscar = findViewById(R.id.editIDBuscar)

        //Relacionar al arreglo de cadenas de Strings.xml
        val productos: Array<String> =
            resources.getStringArray(R.array.lista_productos)
        //Definir la forma de mostrar las opciones, lista simple
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                productos)

        //Asociar el listado de productos con el EditText tipo autoComplete
        type.setAdapter(adapter)

    }//override

    fun agregar(view: View?){
        if(name.text.isNotEmpty() && ID.text.isNotEmpty()){

            val id = ID.text.toString().toInt()

            if(!motocicletas.containsKey(id)){

                //Objeto de arreglo clase motocicleta
                val motocicleta1 = Motocicleta("","",0,0.0,0,
                    null,"",0.0)

                motocicleta1.nombre = name.text.toString()
                motocicleta1.tipo = type.text.toString()
                motocicleta1.anio = year.text.toString().toInt()
                motocicleta1.altura = height.text.toString().toDouble()
                motocicleta1.gasolina = gasoline.text.toString().toFloat()
                motocicleta1.descripcion = description.text.toString()
                motocicleta1.precio = price.text.toString().toDouble()
                motocicleta1.Id = id //Se esta asignando un valor id al atributo Id de un objeto
                motocicletas[id] = motocicleta1 //Estás agregando una motocicleta al mapa

                Toast.makeText(this,"Registrada Correctamente.",
                Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this,"Esta lleno el arreglo",
                    Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this,"No se pudo registrar.",
                Toast.LENGTH_LONG).show()
        }//else
    }

    fun limpiarCampos(view: View?){
        name.text.clear()
        type.text.clear()
        year.text.clear()
        height.text.clear()
        ID.text.clear()
        gasoline.text.clear()
        description.text.clear()
        price.text.clear()
        buscar.text.clear()
    }

    @SuppressLint("SuspiciousIndentation")
    fun buscar(view: View?){

        //Se obtiene el texto ingresado en un campo de búsqueda y se almacena como una cadena
        var idBuscar = buscar.text.toString()

        if(idBuscar.isNotEmpty()){
            // Si idBuscar no está vacío, se convierte a un número entero y se almacena en la variable id.
            val id = idBuscar.toInt()
                if(motocicletas.containsKey(id)){ //  Se verifica si el mapa motocicletas contiene una entrada con la clave id

                    //Si la motocicleta con el identificador id existe en el mapa, se recupera
                    // y almacena en la variable
                    val motocicletaEncontrada = motocicletas[id]!!

                    val mostrarNombre = findViewById<EditText>(R.id.editNombre)
                    mostrarNombre.setText(motocicletaEncontrada.nombre)

                    val mostrarTipo = findViewById<AutoCompleteTextView>(R.id.autoCompleteTipo)
                    mostrarTipo.setText(motocicletaEncontrada.tipo)

                    val  mostrarAnio = findViewById<EditText>(R.id.editAnio)
                    mostrarAnio.setText(motocicletaEncontrada.anio.toString())

                    val mostrarAltura = findViewById<EditText>(R.id.editAltura)
                    mostrarAltura.setText(motocicletaEncontrada.altura.toString())

                    val mostrarId = findViewById<EditText>(R.id.editId)
                    mostrarId.setText(motocicletaEncontrada.Id.toString())

                    val mostrarGasolina = findViewById<EditText>(R.id.editGasolina)
                    mostrarGasolina.setText(motocicletaEncontrada.gasolina.toString())

                    val mostrarDescripcion = findViewById<EditText>(R.id.editDescripcion)
                    mostrarDescripcion.setText(motocicletaEncontrada.descripcion)

                    val mostrarPrecio = findViewById<EditText>(R.id.editPrecio)
                    mostrarPrecio.setText(motocicletaEncontrada.precio.toString())

                    Toast.makeText(
                        this, "Busqueda realizada",
                        Toast.LENGTH_LONG).show()
            }
        }
    }
    fun onClick(v: View){
        when(v?.id){
            R.id.btnRegistrar ->{
                agregar(v)
                limpiarCampos(v)
            }
            R.id.btnLimpiar ->{
                limpiarCampos(v)
                Toast.makeText(
                    this, "Campos Vacios",
                Toast.LENGTH_LONG).show()
            }
            R.id.btnBuscar ->{
                buscar(v)
            }
        }
    }//onClick
}//class