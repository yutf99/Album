package k2.hot.white.library.album.entity;

/**
 * 图片选择器View层接口
 * <p/>
 * Created by Clock on 2016/3/21.
 */
public interface ImageChooseView {

    /**
     * 刷新图片的计数器
     *
     * @param imageInfo 进行操作的文件信息
     */
    public void refreshSelectedCounter(ImageInfo imageInfo);

}
