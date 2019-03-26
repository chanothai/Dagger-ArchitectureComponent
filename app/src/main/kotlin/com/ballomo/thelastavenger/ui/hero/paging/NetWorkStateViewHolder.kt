package com.ballomo.thelastavenger.ui.hero.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ballomo.shared.data.NetworkState
import com.ballomo.shared.data.Status
import com.ballomo.thelastavenger.R

class NetWorkStateViewHolder(
    val view: View,
    private val retryCallback:() -> Unit): RecyclerView.ViewHolder(view) {
    private val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
    private val retry = view.findViewById<Button>(R.id.retry_button)
    private val errorMsg = view.findViewById<TextView>(R.id.error_msg)

    init {
        retry.setOnClickListener {
            retryCallback.invoke()
        }
    }

    fun bindTo(netWorkState: NetworkState?) {
        progressBar.visibility =
            toVisibility(netWorkState?.status == Status.RUNNING)
        retry.visibility =
            toVisibility(netWorkState?.status == Status.FAILED)
        errorMsg.visibility =
            toVisibility(netWorkState?.status != null)
        errorMsg.text = netWorkState?.msg
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetWorkStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.network_state_layout, parent, false)
            return NetWorkStateViewHolder(view, retryCallback)
        }
        fun toVisibility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            }else{
                View.GONE
            }
        }
    }
}