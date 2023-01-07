package com.amnpa.sbb.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.amnpa.sbb.R
import kotlinx.android.synthetic.main.game_card.view.*

class GameAdapter(
    private val games: MutableList<Game>,
    private var competitions: HashMap<Int, Competition>
) : RecyclerView.Adapter<GameAdapter.ResultViewHolder>() {

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.game_card,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val curResult = games[position]

        holder.itemView.apply {
            textViewGame.text = curResult.team1 + " - " + curResult.team2
            val competition = competitions[curResult.competitionId]
            textViewGameCompetition.text = competition!!.name
            textViewGameDatetime.text = curResult.date
//            betDrawButton.textOff = "X\n${curResult.drawOdds}"
//            betDrawButton.textOn = "X\n${curResult.drawOdds}"
//            betHostButton.textOff = "1\n${curResult.team1Odds}"
//            betHostButton.textOn = "1\n${curResult.team1Odds}"
//            betVisitorButton.textOff = "2\n${curResult.team2Odds}"
//            betVisitorButton.textOn = "2\n${curResult.team2Odds}"
//            betHostButton.setOnClickListener { button ->
//                turnOnButton(button as ToggleButton)
//                turnOffButton(betDrawButton as ToggleButton)
//                turnOffButton(betVisitorButton as ToggleButton)
//            }
//            betVisitorButton.setOnClickListener { button ->
//                turnOnButton(button as ToggleButton)
//                turnOffButton(betDrawButton as ToggleButton)
//                turnOffButton(betHostButton as ToggleButton)
//            }
//            betDrawButton.setOnClickListener { button ->
//                turnOnButton(button as ToggleButton)
//                turnOffButton(betHostButton as ToggleButton)
//                turnOffButton(betVisitorButton as ToggleButton)
//            }
            try {
                imageViewFlag.setImageDrawable(
                    when (competition.country.lowercase()) {
                        "spain" -> resources.getDrawable(R.drawable.spain)
                        "netherlands" -> resources.getDrawable(R.drawable.netherlands)
                        "poland" -> resources.getDrawable(R.drawable.poland)
                        "germany" -> resources.getDrawable(R.drawable.germany)
                        "france" -> resources.getDrawable(R.drawable.france)
                        "italy" -> resources.getDrawable(R.drawable.italy)
                        "england" -> resources.getDrawable(R.drawable.england)
                        "denmark" -> resources.getDrawable(R.drawable.denmark)
                        "portugal" -> resources.getDrawable(R.drawable.portugal)
                        "nationalteams" -> resources.getDrawable(R.drawable.nationalteams)
                        else -> resources.getDrawable(R.drawable.international)
                    }
                )
            } catch(_:java.lang.NullPointerException){}
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun turnOffButton(button: ToggleButton){
        button.setTextColor(R.color.white)
        button.isChecked = false
    }

    @SuppressLint("ResourceAsColor")
    private fun turnOnButton(button: ToggleButton){
        button.setTextColor(R.color.black)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun reloadData(newData: List<Game>, competitionsData: HashMap<Int, Competition>){
        competitions = competitionsData
        games.clear()
        games.addAll(newData)
        notifyDataSetChanged()
    }
}
