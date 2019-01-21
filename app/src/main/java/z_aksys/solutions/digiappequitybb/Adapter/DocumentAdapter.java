package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.ShareDocumentResponse;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<ShareDocumentResponse.share_document> shareDocumentArrayList;


    public DocumentAdapter(Context mContext, ArrayList<ShareDocumentResponse.share_document> shareDocumentArrayList, Fragment fragment) {
        this.mContext = mContext;
        this.shareDocumentArrayList = shareDocumentArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_document, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ShareDocumentResponse.share_document object = shareDocumentArrayList.get(position);

        holder.txt_document_header.setText(object.getName());
        holder.txt_size.setText(object.getSize() + "," + object.getPublish_date());


        holder.docSharePolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AngelPitchUtil.ShowDialog(mContext, object.getDoc_id(), "document", object.getDoc_name());
            }
        });


    }

    @Override
    public int getItemCount() {
        return shareDocumentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_document_header, txt_size;
        ImageView docSharePolicy;

        public MyViewHolder(View view) {
            super(view);
            this.txt_document_header = itemView.findViewById(R.id.txt_document_header);
            this.txt_size = itemView.findViewById(R.id.txt_size);
            this.docSharePolicy = itemView.findViewById(R.id.docSharePolicy);


        }
    }
}