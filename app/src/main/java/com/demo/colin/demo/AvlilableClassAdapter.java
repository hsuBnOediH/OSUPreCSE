package com.demo.colin.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class AvlilableClassAdapter extends RecyclerView.Adapter<AvlilableClassAdapter.ViewHolder>{

    private ArrayList<String> classList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_class);

        }
    }

    /**
     * 用于把要展示的数据源传进来，classList，我们后续的操作都将在这个数据源的基础上进行。
     * @param avlilableClass
     */
    public AvlilableClassAdapter(ArrayList<String> avlilableClass) {
        this.classList = avlilableClass;
    }

    /**
     * 用于创建ViewHolder实例的，并把加载出来的布局传入到构造函数当中，最后将viewholder的实例返回
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avliable_class_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，这里我们通过
     * position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的Imageview和textview当中即可，
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String className = classList.get(position);
        holder.textView.setText(className);

    }



    @Override
    public int getItemCount() {
        return classList.size();
    }
}