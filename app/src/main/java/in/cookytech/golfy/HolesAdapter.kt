package `in`.cookytech.golfy

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class HolesAdapter : RecyclerView.Adapter<HolesAdapter.HolesViewHolder>() {
    override fun onBindViewHolder(holder: HolesViewHolder, position: Int) {
        holder.mPlusButton.onClick {
            mHoles[position].mHoleStrokeCount++
            notifyDataSetChanged()
        }
        holder.mMinusButton.onClick {
            if (mHoles[position].mHoleStrokeCount > 0) {
                mHoles[position].mHoleStrokeCount--
                notifyDataSetChanged()
            }
            else{
                holder.mCtx.toast("Can't put negative holes dude!!")
            }
        }
        holder.mHoleLabel.text = mHoles[position].mHoleLabel
        holder.mHoleCount.text = mHoles[position].mHoleStrokeCount.toString()
    }

    override fun getItemCount(): Int = mHoles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolesViewHolder {
        val v = parent
                .context
                .layoutInflater
                .inflate(R.layout.item, parent, false )
        return HolesViewHolder(v)
    }

    class HolesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mHoleLabel  = itemView.itemHoleNumber!!
        val mHoleCount = itemView.itemHoleCount!!
        val mPlusButton = itemView.itemPlusButton!!
        val mMinusButton = itemView.itemMinusButton!!
        val mCtx = itemView.context!!
    }
}