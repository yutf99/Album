package k2.hot.white.library.album.presenter;

import android.content.Context;
import android.support.v4.app.LoaderManager;

import k2.hot.white.library.album.entity.AlbumView;
import k2.hot.white.library.album.entity.AlbumViewData;
import k2.hot.white.library.album.scanner.ImageScannerModel;
import k2.hot.white.library.album.scanner.ImageScannerModelImpl;


public class ImageScannerPresenterImpl implements ImageScannerPresenter {

    private ImageScannerModel mScannerModel;
    private AlbumView mAlbumView;

    public ImageScannerPresenterImpl(AlbumView albumView) {
        mScannerModel = new ImageScannerModelImpl();
        mAlbumView = albumView;
    }

    @Override
    public void startScanImage(final Context context, LoaderManager loaderManager) {
        mScannerModel.startScanImage(context, loaderManager, new ImageScannerModel.OnScanImageFinish() {


            @Override
            public void onFinish(ImageScanResult imageScanResult) {
                if (mAlbumView != null) {
                    AlbumViewData albumData = mScannerModel.archiveAlbumInfo(context, imageScanResult);
                    mAlbumView.refreshAlbumData(albumData);
                }
            }

        });
    }
}
