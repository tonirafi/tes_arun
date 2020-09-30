package com.sirem.tesaruna

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import com.sirem.tesaruna.model.DataNews
import kotlinx.android.synthetic.main.item_post.view.*


class ListPostAdapter(private var ctx: Context) :
    RecyclerView.Adapter<ListPostAdapter.ViewHolder>(){
    private val ITEM_VIEW_TYPE_CONTENT = 0
    private val ITEM_VIEW_TYPE_LOADING = 1


    private var listdata: ArrayList<DataNews>? = null
    private var listOrderIdGroup: ArrayList<String>? = ArrayList<String>()
    private var isLoadingAdded = false



    init {
        this.listdata = java.util.ArrayList<DataNews>()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        ctx = parent.context


        return when (viewType) {
            ITEM_VIEW_TYPE_CONTENT -> ViewHolderContent(
                layoutInflater.inflate(R.layout.item_post, parent, false)
            )
            else -> ViewHolderLoading(
                layoutInflater.inflate(R.layout.item_data_loading, parent, false)
            )
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, i: Int) {

        when (getItemViewType(i)) {
            ITEM_VIEW_TYPE_CONTENT -> {
                holder.itemView.apply {
                    tv_title.text =listdata?.get(i)?.title
                    tv_body.text=listdata?.get(i)?.body
                }

            }
            else -> {
                /** nothing to do in here */
            }
        }


    }








    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getListData(): ArrayList<DataNews>? {
        return listdata
    }


    override fun getItemCount(): Int {
        return if (listdata == null) 0 else listdata!!.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == listdata?.size!! - 1 && isLoadingAdded) ITEM_VIEW_TYPE_LOADING else ITEM_VIEW_TYPE_CONTENT
    }

    fun add(r: DataNews) {

        listdata?.add(r)
        notifyItemInserted(listdata?.size!! - 1)
    }

    fun addAll(dataList: ArrayList<DataNews>) {

        for (result in dataList) {
            add(result)
        }
    }

    fun remove(r: DataNews) {
        val position = listdata?.indexOf(r)
        if (position != null) {
            if (position > -1) {
                listdata?.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    fun clear() {
        listOrderIdGroup=ArrayList<String>()
//        (ctx as ListPesananActivity).convertArrayOrder(listOrderIdGroup!!)
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }


    fun addLoadingFooter() {
        isLoadingAdded = true
//        add(Pesanan)
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = listdata?.size?.minus(1)
        val result = position?.let { getItem(it) }

        if (result != null) {
            position.let { listdata?.removeAt(it) }
            position.let { notifyItemRemoved(it) }
        }
    }

    fun getItem(position: Int): DataNews {
        return listdata?.get(position)!!
    }

    fun hideLoader() {
        isLoadingAdded = false

    }


    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

    inner class ViewHolderContent(itemView: View) : ViewHolder(itemView)

    inner class ViewHolderLoading(itemView: View?) : ViewHolder(itemView)


}