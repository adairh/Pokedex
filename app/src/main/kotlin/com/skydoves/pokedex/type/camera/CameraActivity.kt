package com.skydoves.pokedex.type.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.os.Bundle
import android.provider.MediaStore
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TabHost
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.pokedex.R
import com.skydoves.pokedex.core.model.Pokemon
import com.skydoves.pokedex.ml.Pokedex91
import com.skydoves.pokedex.ui.details.PKMDActivity
import com.skydoves.pokedex.ui.getPokemonId
import com.skydoves.pokedex.utils.TabHostUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.min


class CameraActivity : AppCompatActivity() {

  var result: TextView? = null
  var confidence: TextView? = null
  var imageView: ImageView? = null
  var picture: Button? = null
  var imageSize: Int = 224

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_camera)
    val tabHost = findViewById<TabHost>(R.id.tabHost)
    TabHostUtils.setupTabHost(this, tabHost, 1)

    result = findViewById<TextView>(R.id.resultText)
    confidence = findViewById<TextView>(R.id.confidenceText)
    imageView = findViewById<ImageView>(R.id.imageView)
    picture = findViewById<Button>(R.id.takePictureButton)

    picture?.setOnClickListener(View.OnClickListener {
      // Launch camera if we have permission
      if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 1)
      } else {
        //Request camera permission if we don't have it.
        requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
      }
    })
  }


  fun classifyImage(image: Bitmap) {
    try {
      val model = Pokedex91.newInstance(this)
      // Creates inputs for reference.
      val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
      val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
      byteBuffer.order(ByteOrder.nativeOrder())
      // get 1D array of 224 * 224 pixels in image
      val intValues = IntArray(imageSize * imageSize)
      image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)
      // iterate over pixels and extract R, G, and B values. Add to bytebuffer.
      var pixel = 0
      for (i in 0 until imageSize) {
        for (j in 0 until imageSize) {
          val `val` = intValues[pixel++] // RGB
          byteBuffer.putFloat(((`val` shr 16) and 0xFF) * (1f / 255f))
          byteBuffer.putFloat(((`val` shr 8) and 0xFF) * (1f / 255f))
          byteBuffer.putFloat((`val` and 0xFF) * (1f / 255f))
        }
      }



      inputFeature0.loadBuffer(byteBuffer)
      // Runs model inference and gets result.
      val outputs = model.process(inputFeature0)
      val outputFeature0 = outputs.outputFeature0AsTensorBuffer
      val confidences = outputFeature0.floatArray
      // find the index of the class with the biggest confidence.
      var maxPos = 0
      var maxConfidence = 0f
      for (i in confidences.indices) {
        if (confidences[i] > maxConfidence) {
          maxConfidence = confidences[i]
          maxPos = i
        }
      }
      val classes = arrayOf(
        "Abra", "Aerodactyl", "Alakazam", "Arbok", "Arcanine", "Articuno", "Beedrill", "Bellsprout",
        "Blastoise", "Bulbasaur", "Butterfree", "Caterpie", "Chansey", "Charizard", "Charmander", "Charmeleon", "Clefable", "Clefairy", "Cloyster", "Cubone", "Dewgong",
        "Diglett", "Ditto", "Dodrio", "Doduo", "Dragonair", "Dragonite", "Dratini", "Drowzee", "Dugtrio", "Eevee", "Ekans", "Electabuzz",
        "Electrode", "Exeggcute", "Exeggutor", "Farfetchd", "Fearow", "Flareon", "Gastly", "Gengar", "Geodude", "Gloom",
        "Golbat", "Goldeen", "Golduck", "Golem", "Graveler", "Grimer", "Growlithe", "Gyarados", "Haunter", "Hitmonchan",
        "Hitmonlee", "Horsea", "Hypno", "Ivysaur", "Jigglypuff", "Jolteon", "Jynx", "Kabuto",
        "Kabutops", "Kadabra", "Kakuna", "Kangaskhan", "Kingler", "Koffing", "Krabby", "Lapras", "Lickitung", "Machamp",
        "Machoke", "Machop", "Magikarp", "Magmar", "Magnemite", "Magneton", "Mankey", "Marowak", "Meowth", "Metapod",
        "Mew", "Mewtwo", "Moltres", "Mrmime", "Muk", "Nidoking", "Nidoqueen", "Nidorina", "Nidorino", "Ninetales",
        "Oddish", "Omanyte", "Omastar", "Onix", "Paras", "Parasect", "Persian", "Pidgeot", "Pidgeotto", "Pidgey",
        "Pikachu", "Pinsir", "Poliwag", "Poliwhirl", "Poliwrath", "Ponyta", "Porygon", "Primeape", "Psyduck", "Raichu",
        "Rapidash", "Raticate", "Rattata", "Rhydon", "Rhyhorn", "Sandshrew", "Sandslash", "Scyther", "Seadra",
        "Seaking", "Seel", "Shellder", "Slowbro", "Slowpoke", "Snorlax", "Spearow", "Squirtle", "Starmie", "Staryu",
        "Tangela", "Tauros", "Tentacool", "Tentacruel", "Vaporeon", "Venomoth", "Venonat", "Venusaur", "Victreebel",
        "Vileplume", "Voltorb", "Vulpix", "Wartortle", "Weedle", "Weepinbell", "Weezing", "Wigglytuff", "Zapdos", "Zubat"
      )
      //result!!.text = classes[maxPos]
      val r = classes[maxPos];
      val spannableString = SpannableString(r)
      val clickableSpan = ConfidenceClickableSpan(r)

      var s = ""
      var i = maxPos
      s += String.format("%s: %.1f%%\n", classes[i], confidences[i] * 100)

      if (confidences[i]*100 > 80) {

        spannableString.setSpan(clickableSpan, 0, r.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        result!!.movementMethod = LinkMovementMethod.getInstance()
        result!!.text = spannableString



        confidence!!.text = s
        // Releases model resources if no longer used.
        model.close()
      } else {
        result!!.text = "Not found"
        confidence!!.text = "Pokemon cant be determinded due to image quality or there is no Pokemon in the image"
      }
    } catch (e: IOException) {
      // TODO Handle the exception
    }
  }


  public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == 1 && resultCode == RESULT_OK) {
      var image = data!!.extras!!["data"] as Bitmap?
      val dimension =
        min(image!!.width.toDouble(), image!!.height.toDouble()).toInt()
      image = ThumbnailUtils.extractThumbnail(image, dimension, dimension)
      imageView!!.setImageBitmap(image)
      image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false)
      classifyImage(image)
    }
    super.onActivityResult(requestCode, resultCode, data)
  }


  private inner class ConfidenceClickableSpan(private val name: String) : ClickableSpan() {
    override fun onClick(widget: View) {
      println("CameraActivity $name")
       val lowerName = name.lowercase()
      runBlocking {
        val pokemonId = getPokemonId(lowerName)
        println("Pokemon ID: $pokemonId") // Output: Pokemon ID: 150
        //PKMDActivity.startActivity(this@CameraActivity, Pokemon(0, lowerName, "https://pokeapi.co/api/v2/pokemon/$lowerName/"))
        PKMDActivity.startActivity(this@CameraActivity, Pokemon(0, lowerName, "https://pokeapi.co/api/v2/pokemon/$pokemonId/"))
        //this@CameraActivity.startActivity(Intent(this@CameraActivity, DetailActivity::class.java))
      }
    }




    override fun updateDrawState(ds: TextPaint) {
      super.updateDrawState(ds)
      ds.isUnderlineText = true // Underline the text
    }
  }




}