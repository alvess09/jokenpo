@file:Suppress("UNUSED_EXPRESSION")

package alves.ariel.jokenpo

import alves.ariel.jokenpo.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var scorePlayer = 0
    private var scoreMaquina = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //valores definidos para inicializacao das variaveis
        var opcaoPlayer: Int = -1
        var opcaoMaquina: Int = -1

        val btnPedra = binding.ivPedra
        btnPedra.setOnClickListener {
            opcaoPlayer = defineJogadaDoPlayer(0)
            Toast.makeText(this, "pedra selecionda", Toast.LENGTH_SHORT).show()
            opcaoMaquina = defineJogadaDaMaquina()
            verificavencedor(opcaoMaquina, opcaoPlayer)
        }


        val btnPapel = binding.ivPapel
        btnPapel.setOnClickListener {
            opcaoPlayer = defineJogadaDoPlayer(1)
            Toast.makeText(this, "papel selecionado", Toast.LENGTH_SHORT).show()
            opcaoMaquina = defineJogadaDaMaquina()
            verificavencedor(opcaoMaquina, opcaoPlayer)
        }


        val btnTesoura = binding.ivTesoura
        btnTesoura.setOnClickListener {
            opcaoPlayer = defineJogadaDoPlayer(2)
            Toast.makeText(this, "tesoura selecionda", Toast.LENGTH_SHORT).show()
            opcaoMaquina = defineJogadaDaMaquina()
            verificavencedor(opcaoMaquina, opcaoPlayer)
        }

        Log.d("opcaoSelecionada", "opção do Player: $opcaoPlayer")




    }


    private fun defineJogadaDaMaquina(): Int {
        //gera escolha aleatoria da maquina
        val opcao = Random.nextInt(0, 3)
        when (opcao) {
            0 -> trocaImagem(0)
            1 -> trocaImagem(1)
            2 -> trocaImagem(2)
        }

        Log.d("opcaoSelecionada", "opção da Maquina: $opcao")
        return opcao
    }

    private fun trocaImagem(i: Int) {
        val imgEscolhaDoApp = findViewById<ImageView>(R.id.iv_escolhaDoApp)
        when (i) {
            0 -> imgEscolhaDoApp.setImageResource(R.drawable.pedra)
            1 -> imgEscolhaDoApp.setImageResource(R.drawable.papel)
            2 -> imgEscolhaDoApp.setImageResource(R.drawable.tesoura)
            else -> imgEscolhaDoApp.setImageResource(R.drawable.padrao)
        }

    }


    private fun defineJogadaDoPlayer(opcao: Int): Int {
        val pedra: Int = 0
        val papel: Int = 1
        val tesoura: Int = 2

        val selecionado =
            //valor padrão inicilaizado }
            when (opcao) {
                0 -> pedra
                1 -> papel
                2 -> tesoura
                else -> -1

            }
        return selecionado
    }

    private fun verificavencedor(opcaoMaquina: Int, opcaoPlayer: Int) {

        /** Regras do JokenPo
         * 0 (pedra) vence  2 (tesoura)
         * 1 (papel) vence  0 (pedra)
         * 2 (tesoura) vence 1 (papel) */

        //falta ajustar para funcionar o score
        val vencedor =
            if ((opcaoPlayer == 0 && opcaoMaquina == 2) || (opcaoPlayer == 1 && opcaoMaquina == 0) || (opcaoPlayer == 2 && opcaoMaquina == 1)) {
                this.scorePlayer++ //incrementa a pontuação do jogador
                "Você Ganhou!"

            } else if ((opcaoMaquina == 0 && opcaoPlayer == 2) || (opcaoMaquina == 1 && opcaoPlayer == 0) || (opcaoMaquina == 2 && opcaoPlayer == 1)) {
                this.scoreMaquina++ //incrementa a pontuação da Maquina(APP)
                "Você Perdeu!"

            } else {
                "Empate!"
            }
        val tvResultado = findViewById<TextView>(R.id.tv_resultado)
        tvResultado.text = vencedor

        val tvScoreplayer = findViewById<TextView>(R.id.tv_ptos_voce)
        val tvScoreMaquina = findViewById<TextView>(R.id.tv_ptos_maquina)



        tvScoreplayer.text = "$scorePlayer"
        tvScoreMaquina.text = "$scoreMaquina"

        Log.d("saldos", "saldoPlayer: $scorePlayer , saldoMaquina: $scoreMaquina ")


    }

}




